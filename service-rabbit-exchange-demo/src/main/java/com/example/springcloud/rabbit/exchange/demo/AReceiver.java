package com.example.springcloud.rabbit.exchange.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 10:28
 * Description:
 */
@Component
@RabbitListener(queues = {"aaa"})
public class AReceiver {

    @RabbitHandler
    public void process(String context) {
        System.err.println("*******************Receiver A: " + context);
    }
}
