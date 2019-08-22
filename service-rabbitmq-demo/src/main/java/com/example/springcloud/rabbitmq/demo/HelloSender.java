package com.example.springcloud.rabbitmq.demo;

import com.example.springcloud.rabbitmq.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息发送确认回调
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         *
         * @param correlationData
         * @param b 是否接收消息成功
         * @param s 错误信息
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.out.println("[消息反馈：====>]" + correlationData);
            System.out.println(s);
            if (b) {
                System.out.println("消息发送 success");
            } else {
                System.err.println("消息发送失败");
            }
        }
    };


    public void send() {
        String context = "hello -> " + new Date();
        System.err.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(int i) {
        String context = "hello " + i + "-> " + new Date();
        System.err.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("test.a","routingKey.a", context);
    }

    public void send(User user) {
        System.err.println("send object user：" + user);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(user.getId() + "");
        this.rabbitTemplate.setConfirmCallback(confirmCallback);
        this.rabbitTemplate.convertAndSend("test.a", "routingKey.a", user, correlationData);
    }
}
