package com.example.springcloud.rabbit.exchange.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 10:29
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private ServiceSender serviceSender;

    @Test
    public void hello() {
        serviceSender.send("hello");
    }

    @Test
    public void helloA() {
        serviceSender.sendA("hello");
    }

    @Test
    public void helloB() {
        serviceSender.sendB("helloB");
    }
}
