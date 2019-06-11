package com.demo.springboot.springboot.dynamic.datasource.demo.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/06/11  下午 03:56
 * Description:
 */
@Accessors(chain = true)
@Data
@ToString
public class User {

    private String id;

    private String username;

    private String password;

    private int gender;

    private int age;
}
