package com.holun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.holun.mapper") //如果Mapper接口过多,建议直接扫描指定包下的mapper接口，为这些mapper接口生成动态代理对象并注入到容器
public class Springboot08MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08MybatisApplication.class, args);
    }

}
