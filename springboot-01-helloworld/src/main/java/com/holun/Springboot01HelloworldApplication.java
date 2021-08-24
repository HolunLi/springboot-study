package com.holun;

import com.holun.config.MyConfig;
import com.holun.entity.Cat;
import com.holun.entity.Dog;
import com.holun.entity.Master;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.ViewResolver;

import java.util.Arrays;

/**
 * @SpringBootApplication 注解标记的类，是一个主程序类/主配置类，表示这是一个springboot应用
 * SpringBoot就是运行这个类的main方法来启动SpringBoot应用
 */
@SpringBootApplication
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        /*启动SpringBoot应用
        SpringApplication.run(Springboot01HelloworldApplication.class, args);*/

        //返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(Springboot01HelloworldApplication.class, args);
       // 查看IOC容器中包含哪些组件（注入到容器中的bean，就是一个组件，组件由IOC容器管理）
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out :: println);

        //获取组件cat（通过组件名，获取组件）
        Cat cat1 = run.getBean("cat", Cat.class);
        Cat cat2 = run.getBean("cat", Cat.class);
        System.out.println(cat1 == cat2); //返回true，说明容器中的组件，默认是单例的

        //获取组件myDog
        Dog myDog = run.getBean("myDog", Dog.class);
        //获取组件master
        Master master = run.getBean("master", Master.class);
        System.out.println(master.getCat() == cat1); //返回true
        System.out.println(master.getDog() == myDog); //返回true

        //配置类本身也是容器中的组件
        MyConfig myConfig = run.getBean(MyConfig.class);
        Cat cat = myConfig.cat();
        System.out.println(cat == cat1); //返回true

        //判断容器中是否包含指定的组件
        boolean flag = run.containsBean("master");
        System.out.println(flag);

        //查看容器中组件的数量
        System.out.println(run.getBeanDefinitionCount());

        System.out.println("=====================");
        String[] names = run.getBeanNamesForType(ViewResolver.class);
        Arrays.stream(names).forEach(System.out :: println);
    }
}
