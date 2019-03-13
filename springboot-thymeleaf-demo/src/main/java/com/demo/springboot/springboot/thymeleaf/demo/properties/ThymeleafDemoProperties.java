package com.demo.springboot.springboot.thymeleaf.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:07
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "thy.demo")
public class ThymeleafDemoProperties {

    private String username;

    private String password;

    public ThymeleafDemoProperties() {
        System.out.println("ThymeleafDemoProperties....");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ThymeleafDemoProperties{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
