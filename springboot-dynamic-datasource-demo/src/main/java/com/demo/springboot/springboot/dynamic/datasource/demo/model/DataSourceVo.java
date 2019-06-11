package com.demo.springboot.springboot.dynamic.datasource.demo.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/06/11  下午 03:05
 * Description:
 */
@ToString
@Data
@Accessors(chain = true)
public class DataSourceVo {

    private String type;

    private String driver;

    private String url;

    private String username;

    private String password;
}
