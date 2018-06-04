package com.example.springcloud.service.feign.demo.service;

import com.example.springcloud.service.feign.demo.hystric.HelloServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2018/5/24  17:35
 * Description:
 */
@FeignClient(value = "service-client", fallback = HelloServiceHystric.class)
public interface HelloService {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    String hello(@PathVariable("name") String name);
}
