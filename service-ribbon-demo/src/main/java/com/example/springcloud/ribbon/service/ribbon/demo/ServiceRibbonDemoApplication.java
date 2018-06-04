package com.example.springcloud.ribbon.service.ribbon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient //向服务中心注册
@EnableHystrix // 开启 hystrix
public class ServiceRibbonDemoApplication {

    @Bean
    @LoadBalanced //表明该 restTemplate 开启负载均衡功能
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonDemoApplication.class, args);
    }
}
