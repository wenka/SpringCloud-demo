package com.demo.springboot.springboot.thymeleaf.demo;

import com.demo.springboot.springboot.thymeleaf.demo.configuration.ThymeleafDemoMarkerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:00
 * Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ThymeleafDemoMarkerConfiguration.class)
public @interface EnableThymeleafDemo {
}
