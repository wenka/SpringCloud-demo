package com.demo.springboot.springboot.redis.demo;

import com.demo.springboot.springboot.redis.demo.aspect.RedisDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/22  下午 03:55
 * Description:
 */
@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @RedisDB(1)
    public void save1() {
        redisTemplate.opsForValue().set("A", 1);
    }

    @RedisDB(2)
    public void save2() {
        redisTemplate.opsForValue().set("B", 2);
    }
}
