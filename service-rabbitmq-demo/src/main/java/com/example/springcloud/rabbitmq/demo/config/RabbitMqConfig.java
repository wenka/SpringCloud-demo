package com.example.springcloud.rabbitmq.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 10:22
 * Description:
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }
}
