package com.demo.springboot.springboot.security.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/05  上午 10:45
 * Description:
 */
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    private String id;

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
