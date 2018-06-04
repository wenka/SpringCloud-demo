package com.example.springcloud.ribbon.service.ribbon.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    /**
     * @param name
     * @return
     * @HystrixCommand ：对该方法创建了断融器的功能
     * fallbackMethod：指定了熔断方法
     */
    @HystrixCommand(fallbackMethod = "error")
    public String helloService(String name) {
        return this.restTemplate.getForObject("http://SERVICE-CLIENT/hello/" + name, String.class);
    }

    public String error(String name) {
        return name + ", is error";
    }
}
