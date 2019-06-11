package com.demo.springboot.springboot.dynamic.datasource.demo;

import com.demo.springboot.springboot.dynamic.datasource.demo.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DynamicDataSourceRegister.class)
public class SpringbootDynamicDatasourceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDynamicDatasourceDemoApplication.class, args);
	}

}
