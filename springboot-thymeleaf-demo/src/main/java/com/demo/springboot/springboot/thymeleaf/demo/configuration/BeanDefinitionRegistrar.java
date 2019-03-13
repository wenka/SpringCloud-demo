package com.demo.springboot.springboot.thymeleaf.demo.configuration;

import com.demo.springboot.springboot.thymeleaf.demo.properties.ThymeleafDemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/13  下午 01:35
 * Description:
 */
public class BeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Autowired
    private ThymeleafDemoProperties thymeleafDemoProperties;

    private static String[] basePackages = {
            "com.demo.springboot.springboot.thymeleaf.demo.service",
            "com.demo.springboot.springboot.thymeleaf.demo.controller"
    };

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
//        System.out.println(this.thymeleafDemoProperties.toString());
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry, true);
        scanner.scan(basePackages);
    }
}
