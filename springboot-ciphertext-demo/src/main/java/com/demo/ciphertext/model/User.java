package com.demo.ciphertext.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 01:59
 * Description:
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class User {

    private String id;

    private String name;

    private String password;
}
