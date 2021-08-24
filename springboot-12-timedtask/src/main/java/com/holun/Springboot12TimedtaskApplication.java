package com.holun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启日程安排
public class Springboot12TimedtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot12TimedtaskApplication.class, args);
    }

}
