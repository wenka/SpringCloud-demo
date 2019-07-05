package com.demo.springboot.springboot.security.demo.dao;

import com.demo.springboot.springboot.security.demo.model.Role;
import com.demo.springboot.springboot.security.demo.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/05  上午 10:47
 * Description:
 */
@Repository
public class UserDao {

    private static final Map<String, User> users = new HashMap<>();

    static {
        Role user = new Role(UUID.randomUUID().toString(), "USER");
        Role admin = new Role(UUID.randomUUID().toString(), "ADMIN");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User aa = new User(UUID.randomUUID().toString(), "aa", bCryptPasswordEncoder.encode("123456"), Arrays.asList(user));
        User bb = new User(UUID.randomUUID().toString(), "bb", bCryptPasswordEncoder.encode("123456"), Arrays.asList(admin));
        users.put(aa.getUsername(), aa);
        users.put(bb.getUsername(), bb);
    }

    public User getByName(String name) {
        return users.get(name);
    }
}
