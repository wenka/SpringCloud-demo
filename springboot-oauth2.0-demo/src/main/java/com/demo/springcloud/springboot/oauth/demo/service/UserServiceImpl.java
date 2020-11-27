package com.demo.springcloud.springboot.oauth.demo.service;

import com.demo.springcloud.springboot.oauth.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/11/26  下午 01:35
 * @description:
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private static final List<User> USER_LIST = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        String password = "123456";
        password = this.passwordEncoder.encode(password);
        USER_LIST.add(new User("1", "A", password));
        USER_LIST.add(new User("2", "B", password));
        USER_LIST.add(new User("3", "C", password));
        USER_LIST.add(new User("4", "D", password));
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = USER_LIST.stream().filter(user -> user.getUsername().equals(s)).findFirst().orElse(null);
        if (Objects.isNull(u)) {
            throw new RuntimeException("用户不存在！");
        }
        return u;
    }
}
