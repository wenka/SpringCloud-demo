package com.demo.springboot.springboot.redis.demo;

import com.demo.springboot.springboot.thymeleaf.demo.EnableThymeleafDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableThymeleafDemo
public class SpringbootRedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisDemoApplication.class, args);
	}

}
