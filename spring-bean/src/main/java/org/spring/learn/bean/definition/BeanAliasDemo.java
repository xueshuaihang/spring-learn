package org.spring.learn.bean.definition;

import org.spring.learn.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean的别名Demo
 *
 * @author Shuaihang Xue
 * @date 2021/1/16
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-definition-context.xml");
        User user = beanFactory.getBean("user", User.class);
        User aliasUser = beanFactory.getBean("alias-user", User.class);
        System.out.println("user 与 aliasUser 是否相等：" + (user == aliasUser));
    }
}
