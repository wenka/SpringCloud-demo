package com.demo.springboot.springboot.redis.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/22  下午 03:50
 * Description:
 */
@Component
@Aspect
public class RedisDBChangeAspect {

    @Pointcut("@annotation(com.demo.springboot.springboot.redis.demo.aspect.RedisDB)")
    public void redisDbChange() {
    }

    @Around("redisDbChange()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        RedisDB annotation = targetMethod.getAnnotation(RedisDB.class);
        int db = annotation.value();
        try {
            // 获取当前类的注入属性 redisTemplate
            Object target = joinPoint.getTarget();
            Field[] declaredFields = target.getClass().getDeclaredFields();
            Field redisTemplateField = null;
            for (Field field: declaredFields){
                if (field.getType() == RedisTemplate.class){
                    redisTemplateField = field;
                    break;
                }
            }
            redisTemplateField.setAccessible(true);
            RedisTemplate redisTemplate = (RedisTemplate) redisTemplateField.get(target);
            LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            int database = connectionFactory.getDatabase();

            RedisStandaloneConfiguration standaloneConfiguration = connectionFactory.getStandaloneConfiguration();
            standaloneConfiguration.setDatabase(db);
            connectionFactory.afterPropertiesSet();
            connectionFactory.resetConnection();
            result = joinPoint.proceed();
            System.out.println(targetMethod.getDeclaringClass() + "." + targetMethod.getName() + " 默认DB：" + database + "，切换的DB：" + db);
            standaloneConfiguration.setDatabase(database);
            connectionFactory.afterPropertiesSet();
            connectionFactory.initConnection();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
