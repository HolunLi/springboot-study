package com.holun.config;

import com.holun.entity.Cat;
import com.holun.entity.Dog;
import com.holun.entity.Master;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * proxyBeanMethods是代理bean方法，默认为true
 * Full模式 (proxyBeanMethods = true) 【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 * Lite模式 (proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *
 * 配置类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
 * 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式
 *
 * 总结：配置类中存在组件依赖必须使用Full模式。没有存在组件依赖可使用Lite模式，加速容器启动过程
 */

/**
 * 使用@Import注解，向容器中快速导入指定类型的组件，以该方式导入的组件名为全类名
 * 此时，容器中已经有两个类型为Master的组件。一个组件名为 master，另一个为 com.holun.entity.Master
 *
 * @import注解的另外一种用法: @import(xxxImportSelector.class)
 * xxxImportSelector类实现了ImportSelector接口，返回指定组件的全类名，返回值是一个字符串数组
 * @import + xxxImportSelector.class 可以自定义选择导入哪些组件
 */
@Import(Master.class)
@Configuration(proxyBeanMethods = true) //告诉SpringBoot，这是一个配置类，配置类本身也是组件。配置类 == 配置文件
public class MyConfig {

    /**
     * 使用@Bean注解项向容器中添加组件，添加到容器中的组件默认是单例的。
     * 以方法名作为组件id（组件名）, 返回类型为组件类型，返回值就是组件在容器中的实例
     */
    @Bean
    public Cat cat() {
        return new Cat("阿猫");
    }

    //@Scope 使用Scope注解可以指定组件的作用域
    @Bean("myDog")  //人为指定组件的名字
    public Dog dog() {
        return new Dog("阿狗");
    }

    /**
     * 当容器已存在名为cat和名为myDog的组件时，组件master才会被注入到容器中。
     * 假如我把上面的cat组件注释掉，当前组件就不会被注入容器。
     * 既然没有被注入到容器，再从容器中获取时，就就发生 NoSuchBeanDefinitionException（没有这个bean）
     * @ConditionalOnBean注解 是 @Conditional注解的衍生注解之一。@Conditional注解有哪些衍生的注解，这里不再叙述
     * @Conditional注解 以及其衍生注解都可以被标注类或方法上，当被标注在类（一般指配置类）上时，如果没有满足指定条件
     * 该配置类中通过@bean注解定义的所有组件都不会被注入到容器
     */
    @ConditionalOnBean(name = {"cat", "myDog"})
    @Bean
    public Master master() {
        Master master = new Master();
        //组件依赖（组件master依赖容器中的组件cat和dog）
        master.setCat(cat());
        master.setDog(dog());

        return master;
    }
}
