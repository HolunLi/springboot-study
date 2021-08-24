package com.holun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 模拟服务注册，远程调用
 * dubbo是提供给RPC（远程过程调用）服务的框架，它可以监控服务的生产者者和消费者
 * zookeeper是服务注册中心
 */
@SpringBootApplication
public class ProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServerApplication.class, args);
    }

}
