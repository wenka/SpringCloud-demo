package com.example.springcloud.rabbit.exchange.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  下午 01:21
 * Description:
 */
@Component
public class ServiceSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String context) {
        System.err.println("Sender :" + context);
        this.amqpTemplate.convertAndSend("fanoutExchange", "", context);
    }

    public void sendA(String context) {
        System.err.println("Sender :" + context);
        this.amqpTemplate.convertAndSend("exchange", "fanout.A", context);
    }

    public void sendB(String context) {
        System.err.println("Sender :" + context);
        this.amqpTemplate.convertAndSend("exchange", "fanout.B", context);
    }
}
