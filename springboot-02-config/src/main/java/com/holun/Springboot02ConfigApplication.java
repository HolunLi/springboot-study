package com.holun;

import com.holun.pojo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Arrays;

@SpringBootApplication
//@EnableConfigurationProperties(Person.class)
public class Springboot02ConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springboot02ConfigApplication.class, args);
        String[] names = run.getBeanNamesForType(Person.class);
        Arrays.stream(names).forEach(System.out :: println);
    }

}
