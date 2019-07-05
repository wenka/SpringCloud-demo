package com.demo.springboot.springboot.security.demo.config;

import com.demo.springboot.springboot.security.demo.filter.BeforeLoginFilter;
import com.demo.springboot.springboot.security.demo.provide.JWTAuthProvide;
import com.demo.springboot.springboot.security.demo.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/05  上午 10:18
 * Description:
 * <p>
 * AbstractAuthenticationProcessingFilter auth过滤器
 */
@Configuration
@EnableWebSecurity
//   prePostEnabled = true开启 @PostAuthorize()方法之后 @PreAuthorize()方法之前
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JWTAuthProvide jwtAuthProvide;

    /**
     * 在内存中创建一个认证用户信息
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN");
//        auth.userDetailsService(securityService)
//                .passwordEncoder(new BCryptPasswordEncoder());
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 任何使用 provider 的过滤器都将使用同一组 ProviderManager
         * 若当前ProviderManager 中所有providers 都返回null
         * 则递归调用的 this.parent(AuthenticationManager) 中的 authenticate 方法
         * 直至result != null 或者 所有providers执行完毕
         */
        auth.authenticationProvider(jwtAuthProvide);
    }

    /**
     * addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter)
     * 在 beforeFilter 之前添加 filter
     * <p>
     * addFilterAfter(Filter filter, Class<? extends Filter> afterFilter)
     * 在 afterFilter 之后添加 filter
     * <p>
     * addFilterAt(Filter filter, Class<? extends Filter> atFilter)
     * 在 atFilter 相同位置添加 filter， 此 filter 不覆盖 filter
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
//                .and()
//                .formLogin() //增加 UsernamePasswordAuthenticationFilter 过滤器
//                .loginPage("/login")
//                .permitAll()
                .and()
                .logout()
                .permitAll();

        /**
         * AuthenticationManager --> ProviderManager 管理多个 Providers
         * 循环遍历 Providers 执行 authenticate 方法
         * 若执行结果返回值 不为空 就终止循环
         */
        http.addFilterBefore(new BeforeLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

//        http.authorizeRequests()
//                // 不需要验证
//                .antMatchers("/css/**", "/index").permitAll()
//                // /user 需要 USER 角色
//                .antMatchers("/user/**").hasRole("USER")
//                // /admin 需要 ADMIN 角色
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .csrf().disable()
//                .sessionManagement().disable()
//                .formLogin().disable();

    }
}
