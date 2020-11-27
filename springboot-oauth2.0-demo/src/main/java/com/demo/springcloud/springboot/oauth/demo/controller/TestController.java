package com.demo.springcloud.springboot.oauth.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/11/26  下午 04:58
 * @description:
 */
@RestController
public class TestController {

    /**
     * 请求需携带 <code>access_token</code> 参数方可访问。
     *
     * @param principal
     * @return
     */
    @GetMapping("user/info")
    public Object getUser(Principal principal) {
        return principal;
    }
}
