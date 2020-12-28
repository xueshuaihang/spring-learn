package org.spring.learn.ioc.domain;

import org.springframework.beans.factory.FactoryBean;

/**
 * User的FactoryBean，用来生产User对象
 *
 * @author Shuaihang Xue
 * @date 2020/12/28
 */
public class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(2L);
        user.setName("大帅");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
