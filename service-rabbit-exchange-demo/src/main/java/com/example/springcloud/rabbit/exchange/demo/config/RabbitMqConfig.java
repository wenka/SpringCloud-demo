package com.example.springcloud.rabbit.exchange.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 11:16
 * Description: FanoutExchange
 */
@Configuration
public class RabbitMqConfig {

    @Bean(name = "aQueue")
    public Queue queueA() {
        return new Queue("fanout.a");
    }

    @Bean(name = "bQueue")
    public Queue queueB() {
        return new Queue("fanout.b");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("aQueue") Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("bQueue") Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }
}
