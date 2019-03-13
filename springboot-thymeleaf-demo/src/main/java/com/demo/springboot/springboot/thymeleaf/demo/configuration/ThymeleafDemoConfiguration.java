package com.demo.springboot.springboot.thymeleaf.demo.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:03
 * Description:
 */
@Configuration
@ConditionalOnBean(ThymeleafDemoMarkerConfiguration.Marker.class)
@Import(CommonConfiguration.class)
public class ThymeleafDemoConfiguration {

    ThymeleafDemoConfiguration(){
        System.out.println("ThymeleafDemoConfiguration....");
    }
}
