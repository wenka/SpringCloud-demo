package com.demo.springcloud.springboot.oauth.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.Objects;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/11/30  上午 10:08
 * @description:
 */
@Component
@Slf4j
public class MyWebRequestInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        log.info("WebRequestInterceptor =====> preHandle");
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
        log.info("WebRequestInterceptor =====> postHandle");
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {
        log.info("WebRequestInterceptor =====> afterCompletion");
        if (Objects.nonNull(e)){
            log.error(e.getMessage());
        }
    }
}
