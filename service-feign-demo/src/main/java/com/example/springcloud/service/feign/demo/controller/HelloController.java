package com.example.springcloud.service.feign.demo.controller;

import com.example.springcloud.service.feign.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2018/5/24  17:36
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/feign/hello", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return this.helloService.hello(name);
    }
}
