package org.spring.learn.ioc.container;

import org.spring.learn.ioc.domain.User;
import org.spring.learn.ioc.domain.UserFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 使用注解方式，手动创建{@link AnnotationConfigApplicationContext}作为Ioc容器
 *
 * @author Shuaihang Xue
 * @date 2020/12/28
 */
public class AnnotationApplicationContextAsIocContainer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIocContainer.class);
        // 启动应用上下文
        applicationContext.refresh();
//        lookupCollectionByType(applicationContext);
        useFactoryBean(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

//    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小帅");
        return user;
    }

    @Bean
    public UserFactoryBean userFactoryBean() {
        return new UserFactoryBean();
    }

    private static void useFactoryBean(ApplicationContext applicationContext) {
        User user = (User) applicationContext.getBean("userFactoryBean");
        System.out.println(user);
        UserFactoryBean userFactoryBean = (UserFactoryBean) applicationContext.getBean("&userFactoryBean");
        System.out.println(userFactoryBean);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有User的集合对象：" + userMap);
        }
    }
}
