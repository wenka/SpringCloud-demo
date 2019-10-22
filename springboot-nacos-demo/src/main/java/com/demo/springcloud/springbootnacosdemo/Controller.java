package com.demo.springcloud.springbootnacosdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/10/22  下午 01:44
 * Description:
 */
@RestController
public class Controller {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @GetMapping("hi")
    public Map hi() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("age", String.valueOf(age));
        return map;
    }

}
