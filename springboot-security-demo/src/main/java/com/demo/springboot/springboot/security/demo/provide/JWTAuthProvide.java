package com.demo.springboot.springboot.security.demo.provide;

import com.demo.springboot.springboot.security.demo.model.User;
import com.demo.springboot.springboot.security.demo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/05  下午 04:37
 * Description:
 */
@Component
public class JWTAuthProvide implements AuthenticationProvider {

    @Autowired
    private SecurityService securityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        UserDetails userDetails = securityService.loadUserByUsername(token);

        if (userDetails == null) {
            userDetails = new User("m1", "1","1", null);
            //            throw new RuntimeException("用户不存在");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getAuthorities());
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
