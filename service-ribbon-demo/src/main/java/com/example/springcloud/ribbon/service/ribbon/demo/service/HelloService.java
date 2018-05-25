package com.example.springcloud.ribbon.service.ribbon.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2018/5/23  18:22
 * Description:
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String helloService(String name) {
        return this.restTemplate.getForObject("http://SERVICE-CLIENT/hello/" + name, String.class);
    }
}
