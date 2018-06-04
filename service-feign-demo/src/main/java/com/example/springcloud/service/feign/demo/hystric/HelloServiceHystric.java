package com.example.springcloud.service.feign.demo.hystric;

import com.example.springcloud.service.feign.demo.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2018/6/4  10:33
 * Description:
 */
@Component
public class HelloServiceHystric implements HelloService {
    @Override
    public String hello(String name) {
        return name + " in feign is error";
    }
}
