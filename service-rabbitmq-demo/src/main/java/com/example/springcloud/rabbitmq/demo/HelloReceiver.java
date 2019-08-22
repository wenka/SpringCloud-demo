package com.example.springcloud.rabbitmq.demo;

import com.example.springcloud.rabbitmq.demo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 10:28
 * Description: 监听队列 hello
 */
@Component
@RabbitListener(queues = {"hello"})
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.err.println("*******************Receiver : " + hello);
    }

    @RabbitHandler
    public void process(User user) {
        System.err.println("receiver:" + user);
    }
}
