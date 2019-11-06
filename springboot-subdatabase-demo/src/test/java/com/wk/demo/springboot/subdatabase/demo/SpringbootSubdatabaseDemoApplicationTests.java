package com.wk.demo.springboot.subdatabase.demo;

import com.wk.demo.springboot.subdatabase.demo.dao.UserMapper;
import com.wk.demo.springboot.subdatabase.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = SpringbootSubdatabaseDemoApplication.class)
class SpringbootSubdatabaseDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testOne() {
        User user = new User(UUID.randomUUID().toString(), "a", 18);
        this.userMapper.insertUser(user);
    }

    @Test
    public void testBatch() {
        List<User> userList = new ArrayList<>();
        for (int i=0;i<10;i++){
            User user = new User(UUID.randomUUID().toString(), "a", 18);
            userList.add(user);
        }
        this.userMapper.batchInsertUser(userList);
    }
}
