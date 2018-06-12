package com.example.springcloud.service.zuul.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 开启 Zuul 功能
public class ServiceZuulDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulDemoApplication.class, args);
    }
}
