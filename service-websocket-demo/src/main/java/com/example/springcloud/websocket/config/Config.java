package com.example.springcloud.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/23  下午 01:59
 * Description:
 */
@Configuration
public class Config {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
