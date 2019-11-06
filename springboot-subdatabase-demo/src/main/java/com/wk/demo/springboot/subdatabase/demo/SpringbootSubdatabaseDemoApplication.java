package com.wk.demo.springboot.subdatabase.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wk.demo.springboot.subdatabase.demo.dao")
public class SpringbootSubdatabaseDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSubdatabaseDemoApplication.class, args);
	}

}
