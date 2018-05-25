package com.example.springcloud.service.feign.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //开启Feign的功能
public class ServiceFeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignDemoApplication.class, args);
    }
}
