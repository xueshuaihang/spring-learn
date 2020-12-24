package org.spring.learn.ioc.dependency.injection;

import org.spring.learn.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 *
 * @author Shuaihang Xue
 * @date 2020/12/23
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义的bean
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//        System.out.println(userRepository.getUsers());
        // 依赖来源二：内建依赖依  赖注入的方式获取BeanFactory
        System.out.println(userRepository.getBeanFactory());
        // 依赖查找的方式获取BeanFactory（报错）
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        // 结果说明依赖注入可以注入容器内建的依赖，且依赖注入和依赖查找两者Bean的来源不一致

        System.out.println(userRepository.getObjectFactory().getObject());
        System.out.println(userRepository.getObjectFactory().getObject() == applicationContext);

        // 依赖来源三：容器内建Bean（spring内部初始化的bean）
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }

    private static void whoIsIocContainer(UserRepository userRepository, BeanFactory beanFactory) {
        // 条件不成立
        System.out.println(beanFactory == userRepository.getBeanFactory());

        // BeanFactory 和 ApplicationContext
        // BeanFactory仅仅提供了基础的Ioc能力，而ApplicationContext包含了BeanFactory，也就是说
        // BeanFactory能做的，ApplicationContext都能做，其中调用getBean等方法使用了类似代理方式（持有一个BeanFactory对象）
        // 并且ApplicationContext提供了如下更加全面的功能：
        // 1. 更好的整合了Spring Aop的特征
        // 2. 信息资源处理（用在国际化方面）
        // 3. 事件的发布
        // 4. 可在Web应用中应用于WebApplicationContext

    }

}
