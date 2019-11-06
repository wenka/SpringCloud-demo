package com.wk.demo.springboot.subdatabase.demo.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/11/06  下午 04:15
 * Description:
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String id;

    private String username;

    private Integer age;

}
