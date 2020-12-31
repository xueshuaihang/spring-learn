package org.spring.learn.bean.definition;

import org.spring.learn.ioc.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建示例
 *
 * @author Shuaihang Xue
 * @date 2020/12/31
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1.通过 BeanDefinitionBuild 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 设置属性值
        beanDefinitionBuilder.addPropertyValue("id", 1).addPropertyValue("name", "薛帅航");
        // 获取BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2.通过 AbstractBeanDefinition 获取
        AbstractBeanDefinition abstractBeanDefinition = new GenericBeanDefinition();
        abstractBeanDefinition.setBeanClass(User.class);
        abstractBeanDefinition.getPropertyValues().add("id", 1)
                .add("name", "薛帅航");
    }
}
