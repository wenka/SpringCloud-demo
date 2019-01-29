package com.example.springcloud.rabbitmq.demo;

import com.example.springcloud.rabbitmq.demo.model.User;
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
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void hello() {
        helloSender.send();
    }

    @Test
    public void oneToMany() {
        for (int i = 0; i < 50; i++) {
            helloSender.send(i);
        }
    }

    @Test
    public void sendObj() {
        helloSender.send(new User(1, "A", "aa"));
    }
}
