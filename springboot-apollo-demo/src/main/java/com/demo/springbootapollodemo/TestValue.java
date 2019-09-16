package com.demo.springbootapollodemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/09/11  下午 04:00
 * Description:
 */
@Configuration
public class TestValue {

    @Value("${name:null}")
    private String name;

    @Value("${log.path}")
    private String logPath;

    @Value("${log.name}")
    private String logName;

    @Value("${instance-id}")
    private String instanceId;

    @Value("${port}")
    private String port;

    @PostConstruct
    public void print() {
        System.out.println("name = " + name);
        System.out.println("logPath = " + this.logPath);
        System.out.println("logName = " + this.logName);
        System.out.println("instanceId = " + this.instanceId);
        System.out.println("port = " + this.port);
    }
}
