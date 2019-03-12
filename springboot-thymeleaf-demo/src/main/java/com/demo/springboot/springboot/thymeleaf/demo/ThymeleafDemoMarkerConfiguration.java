package com.demo.springboot.springboot.thymeleaf.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:03
 * Description:
 */
@Configuration
public class ThymeleafDemoMarkerConfiguration {

    @Bean
    public ThymeleafDemoMarkerConfiguration.Marker markerBean() {
        System.out.println("=========> ");
        return new ThymeleafDemoMarkerConfiguration.Marker();
    }

    class Marker {
        Marker() {

        }
    }
}
