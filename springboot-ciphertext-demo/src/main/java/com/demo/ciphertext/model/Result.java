package com.demo.ciphertext.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 01:57
 * Description:
 */
@Getter
@Setter
@Accessors(chain = true)
public class Result {

    private int code;

    private boolean success;

    private String message;

    private Date responseTime = new Date();

    private Object data;

    public static Result success(Object o){
        return new Result().setCode(1).setSuccess(true).setData(o).setMessage("SUCCESS");
    }

}
