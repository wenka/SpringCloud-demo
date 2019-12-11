package com.demo.springboot.springboot.redis.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisDemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void stringRedisTemplateTest() {
        ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
        opsForValue.set("name", "wenka");
        String name = opsForValue.get("name");
        System.out.println(name);
    }

    @Test
    public void redisTemplate() {
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        valueOperations.set("user", new User("wenka", "123"));
        Object user = valueOperations.get("user");
        System.out.println(user);
    }

    @Autowired
    private RedisService redisService;

    @Test
    public void testChange() {
        redisService.save1();
        redisService.save2();
        this.redisTemplate.opsForValue().set("C", "3");
    }

    @Test
    public void testChange2(){
        for (int i = 0; i < 4; i++){
            LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory();
            RedisStandaloneConfiguration standaloneConfiguration = connectionFactory.getStandaloneConfiguration();
            standaloneConfiguration.setDatabase(i);
            connectionFactory.afterPropertiesSet();
            connectionFactory.resetConnection();
            ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
            opsForValue.set(String.valueOf(i),String.valueOf(i));
        }
    }

    @Test
    public void test4(){
        Boolean add = this.redisTemplate.opsForZSet().add("zset", "order1", 1);
        //8
        System.out.println(add);
    }
}
