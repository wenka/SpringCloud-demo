package com.demo.springbootapollodemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/09/11  下午 04:00
 * Description:
 */
@Configuration
public class TestValue {

    @Value("${name:null}")
    private String name;

    @PostConstruct
    public void print() {
        System.out.println("name = " + name);
    }
}
