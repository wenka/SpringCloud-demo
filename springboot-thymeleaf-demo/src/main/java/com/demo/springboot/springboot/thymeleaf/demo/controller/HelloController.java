package com.demo.springboot.springboot.thymeleaf.demo.controller;

import com.demo.springboot.springboot.thymeleaf.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 03:30
 * Description:
 */
@Controller
public class HelloController {

    HelloController(){
        System.out.println("hello controller ....");
    }

    @Autowired
    private HelloService helloService;

    @GetMapping("test")
    public String hello(Model model){
        model.addAttribute("a",helloService.hello());
        return "/main";
    }
}
