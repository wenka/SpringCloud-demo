package com.example.springcloud.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
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

    /**
     * 若无 名为hello 的队列，则创建
     *
     * @return
     */
    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

    /**
     * 若无 名为test.a 的队交换机，则创建
     *
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("test.a");
    }

    /**
     * 将队列 hello 绑定到 test.a 上，同时设置 routing key 为 routingKey.a
     *
     * @param queue
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue queue, TopicExchange topicExchange) {
        BindingBuilder.TopicExchangeRoutingKeyConfigurer to = BindingBuilder.bind(queue).to(topicExchange);
        Binding binding = to.with("routingKey.a");
        return binding;
    }
}
