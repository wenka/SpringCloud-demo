package com.demo.springboot.springboot.thymeleaf.demo.configuration;

import com.demo.springboot.springboot.thymeleaf.demo.properties.ThymeleafDemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


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
