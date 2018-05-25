package com.example.springcloud.ribbon.service.ribbon.demo.controller;

import com.example.springcloud.ribbon.service.ribbon.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2018/5/23  18:26
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return this.helloService.helloService(name);
    }
}
