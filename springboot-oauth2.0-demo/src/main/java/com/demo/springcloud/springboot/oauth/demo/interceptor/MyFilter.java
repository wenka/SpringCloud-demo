package com.demo.springcloud.springboot.oauth.demo.interceptor;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/11/30  下午 01:36
 * @description:
 */
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Filter ==> doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
