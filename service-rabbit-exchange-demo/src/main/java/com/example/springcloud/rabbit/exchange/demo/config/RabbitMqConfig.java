package com.example.springcloud.rabbit.exchange.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/29  上午 11:16
 * Description:
 */
@Configuration
public class RabbitMqConfig {

    @Bean(name = "aQueue")
    public Queue queueA() {
        return new Queue("aaa");
    }

    @Bean(name = "bQueue")
    public Queue queueB() {
        return new Queue("bbb");
    }

    @Bean
    AbstractExchange fanoutExchange() {
//        return new FanoutExchange("fanoutExchange");
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("aQueue") Queue queue, AbstractExchange abstractExchange) {
        if (abstractExchange instanceof FanoutExchange) {
            FanoutExchange exchange = null;
            exchange = (FanoutExchange) abstractExchange;
            return BindingBuilder.bind(queue).to(exchange);
        }else if (abstractExchange instanceof TopicExchange){
            TopicExchange topicExchange = (TopicExchange)abstractExchange;
            return BindingBuilder.bind(queue).to(topicExchange).with("fanout.A");
        }
        return null;
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("bQueue") Queue BMessage, AbstractExchange abstractExchange) {
        if (abstractExchange instanceof FanoutExchange) {
            FanoutExchange exchange = null;
            exchange = (FanoutExchange) abstractExchange;
            return BindingBuilder.bind(BMessage).to(exchange);
        }else if (abstractExchange instanceof TopicExchange){
            TopicExchange topicExchange = (TopicExchange)abstractExchange;
            return BindingBuilder.bind(BMessage).to(topicExchange).with("fanout.B");
        }
        return null;
    }
}
