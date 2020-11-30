package com.demo.springcloud.springboot.oauth.demo.controller;

import com.demo.springcloud.springboot.oauth.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/11/26  下午 04:58
 * @description:
 */
@RestController
@Slf4j
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

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @PostMapping("user")
    public Object postUser(@RequestBody User user) {
        log.info("保存用户：{}", user);
        return "OK";
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @PutMapping("user")
    public Object putUser(@RequestBody User user) {
        log.info("修改用户：{}", user);
        return "OK";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("user/{id}")
    public Object postUser(@PathVariable String id) {
        log.info("删除用户：{}", id);
        return "OK";
    }
}
