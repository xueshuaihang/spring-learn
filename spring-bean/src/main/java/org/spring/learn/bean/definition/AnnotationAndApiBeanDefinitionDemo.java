package org.spring.learn.bean.definition;

import org.spring.learn.ioc.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解和Api方式定义Bean
 * <p>注解：
 * <ol>
 * <li>@Bean</li>
 * <li>@Component</li>
 * <li>@Import</li>
 * </ol>
 * Api：
 * <ol>
 * <li>命名的方式</li>
 * <li>非命名的方式</li>
 * <li>注册配置类的方式{@link AnnotationConfigApplicationContext#register(java.lang.Class[])}</li>
 * </ol>
 *
 * @author Shuaihang Xue
 * @date 2021/1/16
 */
@Import(AnnotationAndApiBeanDefinitionDemo.Config.class) // 会将Config注册为Bean
public class AnnotationAndApiBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 这里是API的形式配置Bean的方式之一，将AnnotationBeanDefinitionDemo作为配置类，同时也作为Bean
        applicationContext.register(AnnotationAndApiBeanDefinitionDemo.class);

        // 命名方式
        registerUserBeanDefinition(applicationContext, "by Api created");
        // 非命名方式
        registerUserBeanDefinition(applicationContext);

        applicationContext.refresh();
        System.out.println(applicationContext.getBeansOfType(User.class));
        System.out.println(applicationContext.getBeansOfType(Config.class));
        System.out.println(applicationContext.getBeansOfType(AnnotationAndApiBeanDefinitionDemo.class));
    }

    /**
     * 利用Api的方式注册Bean
     * 1. 命名的方式
     * 2. 非命名的方式
     * 3. 注册配置类的方式{@link AnnotationConfigApplicationContext#register(java.lang.Class[])}
     *
     * @param registry {@link BeanDefinitionRegistry}的实现类，一般是应用上下文
     * @param name     bean的名称
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String name) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "hang");
        if (StringUtils.hasText(name)) {
            registry.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component // 会将Config注册为Bean
    public static class Config {

        @Bean({"user", "user1"}) // 会将User注册为Bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("小帅");
            return user;
        }
    }

}
