package com.demo.springboot.springboot.thymeleaf.demo.configuration;

import com.demo.springboot.springboot.thymeleaf.demo.properties.ThymeleafDemoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;


/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:03
 * Description:
 */
@ConditionalOnBean(ThymeleafDemoMarkerConfiguration.Marker.class)
@EnableConfigurationProperties(ThymeleafDemoProperties.class)
@Import(BeanDefinitionRegistrar.class)
public class ThymeleafDemoConfiguration{

    ThymeleafDemoConfiguration() {
        System.out.println("ThymeleafDemoConfiguration....");
    }


}
