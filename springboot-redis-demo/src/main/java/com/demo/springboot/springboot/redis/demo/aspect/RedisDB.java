package com.demo.springboot.springboot.redis.demo.aspect;

import java.lang.annotation.*;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/22  下午 03:51
 * Description:
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisDB {
    int value();
}
