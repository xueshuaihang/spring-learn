package org.spring.learn.ioc.domain;

import org.spring.learn.ioc.annotaion.Super;

import java.util.StringJoiner;

/**
 * 超级用户
 *
 * @author Shuaihang Xue
 * @date 2020/12/23
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SuperUser.class.getSimpleName() + "[", "]")
                .add("address='" + address + "'")
                .toString();
    }
}
