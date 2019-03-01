package com.demo.springboot.springboot.redis.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        opsForValue.set("name","wenka");
        String name = opsForValue.get("name");
        System.out.println(name);
    }
    @Test
    public void redisTemplate() {
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        valueOperations.set("user",new User("wenka","123"));
        Object user = valueOperations.get("user");
        System.out.println(user);
    }
}
