package com.example.springcloud.rabbitmq.demo;

import com.example.springcloud.rabbitmq.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 10:24
 * Description:
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hello -> " + new Date();
        System.err.println("Sender: " + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }

    public void send(int i) {
        String context = "hello " + i + "-> " + new Date();
        System.err.println("Sender: " + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }

    public void send(User user) {
        System.err.println("send object user：" + user);
        this.amqpTemplate.convertAndSend("hello", user);
    }
}
