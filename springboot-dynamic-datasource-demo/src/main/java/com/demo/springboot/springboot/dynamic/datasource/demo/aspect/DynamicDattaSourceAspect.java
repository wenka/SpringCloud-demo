package com.demo.springboot.springboot.dynamic.datasource.demo.aspect;

import com.demo.springboot.springboot.dynamic.datasource.demo.annotation.TargetDataSource;
import com.demo.springboot.springboot.dynamic.datasource.demo.config.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/06/11  下午 03:36
 * Description:
 */
@Aspect
@Order(-1)
@Component
public class DynamicDattaSourceAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDattaSourceAspect.class);


    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String value = targetDataSource.value();
        String name = joinPoint.getSignature().getName();
        LOGGER.error(name);
        if (DynamicDataSourceContextHolder.isContainsDataSource(value)) {
            LOGGER.info("使用数据源：{}", value);
            DynamicDataSourceContextHolder.setDatasourceType(value);
        } else {
            LOGGER.error("数据源 {} 不存在", value);
            throw new RuntimeException("数据源" + value + "不存在!");
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        LOGGER.info("清除数据源： {}", targetDataSource.value());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
