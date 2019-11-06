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
        for (int i = 0; i < 10; i++) {
            User user = new User(UUID.randomUUID().toString(), "a" + i, 18);
            userList.add(user);
        }
        this.userMapper.batchInsertUser(userList);
    }

    @Test
    public void queryAll() {
        List<User> userList = this.userMapper.selectAll();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void queryByPage() {
        List<User> userList = this.userMapper.selectAllPage(0, 5);
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void queryById() {
        User user = this.userMapper.selectOne("3514fdd1-8f2f-4f7a-b3ef-5cecc2ada5f5");
        System.out.println(user);
    }

    @Test
    public void queryByName() {
        User user = this.userMapper.selectByName("a0");
        System.out.println(user);
    }
}
