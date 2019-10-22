package com.demo.springcloud.springbootnacosdemo;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "springboot-nacos-demo", type = ConfigType.YAML, autoRefreshed = true)
public class SpringbootNacosDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosDemoApplication.class, args);
    }
}
