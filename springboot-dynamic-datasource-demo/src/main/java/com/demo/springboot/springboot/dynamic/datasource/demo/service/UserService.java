package com.demo.springboot.springboot.dynamic.datasource.demo.service;

import com.demo.springboot.springboot.dynamic.datasource.demo.annotation.TargetDataSource;
import com.demo.springboot.springboot.dynamic.datasource.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/06/11  下午 03:56
 * Description:
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @TargetDataSource("datasource")
    public int insertUser(User user) {
        user.setId(UUID.randomUUID().toString());
        String sql = "INSERT INTO USER(ID,USERNAME,PASSWORD,GENDER,AGE) VALUES(?,?,?,?,?)";
        int update = this.jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getGender(), user.getAge());
        if (true){
            throw new RuntimeException("insertUser 报错");
        }
        return update;
    }


    @TargetDataSource("ds1")
    public int insertUser2(User user) {
        user.setId(UUID.randomUUID().toString());
        String sql = "INSERT INTO USER(ID,USERNAME,PASSWORD,GENDER,AGE) VALUES(?,?,?,?,?)";
        int update = this.jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getGender(), user.getAge());
        return update;
    }

    @TargetDataSource("ds2")
    public int insertUser3(User user) {
        user.setId(UUID.randomUUID().toString());
        String sql = "INSERT INTO USER(ID,USERNAME,PASSWORD,GENDER,AGE) VALUES(?,?,?,?,?)";
        int update = this.jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getGender(), user.getAge());
        return update;
    }

    public int insertUser4(User user) {
        user.setId(UUID.randomUUID().toString());
        String sql = "INSERT INTO USER(ID,USERNAME,PASSWORD,GENDER,AGE) VALUES(?,?,?,?,?)";
        int update = this.jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getGender(), user.getAge());
        return update;
    }
}
