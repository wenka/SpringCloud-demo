package com.demo.springboot.springboot.thymeleaf.demo.service;

import com.demo.springboot.springboot.thymeleaf.demo.properties.ThymeleafDemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:17
 * Description:
 */
@Service
public class HelloService {

    @Autowired
    private ThymeleafDemoProperties thymeleafDemoProperties;

    HelloService() {
        System.out.println("hello service.......");
    }

    public String hello() {
        return "hello " + thymeleafDemoProperties.getUsername() + ",this is in helloService ";
    }
}
