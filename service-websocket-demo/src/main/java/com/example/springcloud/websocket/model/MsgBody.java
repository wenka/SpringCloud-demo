package com.example.springcloud.websocket.model;

import java.io.Serializable;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/23  下午 03:00
 * Description:
 */
public class MsgBody implements Serializable{
    private String username;

    private String message;

    public MsgBody() {
    }

    public MsgBody(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
