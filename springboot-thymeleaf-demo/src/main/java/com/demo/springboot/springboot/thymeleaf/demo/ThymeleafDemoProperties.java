package com.demo.springboot.springboot.thymeleaf.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/03/12  下午 04:07
 * Description:
 */
@ConfigurationProperties(prefix = "thy.demo")
public class ThymeleafDemoProperties {

    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
