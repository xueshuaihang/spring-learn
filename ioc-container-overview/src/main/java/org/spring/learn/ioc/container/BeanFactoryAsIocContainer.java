package org.spring.learn.ioc.container;

import org.spring.learn.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 手动创建{@link BeanFactory}作为Ioc容器
 *
 * @author Shuaihang Xue
 * @date 2020/12/28
 */
public class BeanFactoryAsIocContainer {

    public static void main(String[] args) {
        // DefaultListableBeanFactory也是ApplicationContext默认持有的BeanFactory实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 获取读xml元信息的类
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // xml的classpath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.println("Bean初始化数量：" + beanDefinitionCount);
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有User的集合对象：" + userMap);
        }
    }
}
