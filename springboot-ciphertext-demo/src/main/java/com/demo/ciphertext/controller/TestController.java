package com.demo.ciphertext.controller;

import com.demo.ciphertext.annotation.CipherText;
import com.demo.ciphertext.model.Result;
import com.demo.ciphertext.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 01:40
 * Description:
 */
@Controller
public class TestController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("get")
    @CipherText
    @ResponseBody
    public Result test(@RequestParam String id) {
        return Result.success(id);
    }

    @PostMapping("post")
    @ResponseBody
    public Result test(@RequestBody User user) {
        return Result.success(user);
    }
}
