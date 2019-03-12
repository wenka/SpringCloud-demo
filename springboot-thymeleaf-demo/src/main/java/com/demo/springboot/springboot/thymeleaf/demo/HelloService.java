package com.demo.springboot.springboot.thymeleaf.demo;

import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:17
 * Description:
 */
@Service
public class HelloService {

    public String hello(){
        return "hello ,this is in helloService";
    }
}
