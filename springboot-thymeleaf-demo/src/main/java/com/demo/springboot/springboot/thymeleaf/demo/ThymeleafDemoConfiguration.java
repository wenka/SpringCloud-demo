package com.demo.springboot.springboot.thymeleaf.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:03
 * Description:
 */
@Configuration
@ConditionalOnBean(ThymeleafDemoMarkerConfiguration.Marker.class)
public class ThymeleafDemoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "thy.demo", name = "enabled", havingValue = "true")
    @ConditionalOnBean(HelloService.class)
    public HelloController helloController() {
        System.out.println("===================> 创建 helloController Bean <===================");
        return new HelloController();
    }
}
