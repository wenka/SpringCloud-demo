package com.demo.springbootapollodemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class SpringbootApolloDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApolloDemoApplication.class, args);
	}

}
