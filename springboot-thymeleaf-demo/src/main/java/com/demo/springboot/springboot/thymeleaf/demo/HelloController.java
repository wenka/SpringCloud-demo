package com.demo.springboot.springboot.thymeleaf.demo;

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

    @GetMapping("test")
    public String hello(Model model){
        model.addAttribute("a","hei hei hei");
        return "/main";
    }
}
