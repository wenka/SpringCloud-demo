package com.demo.springboot.springboot.dynamic.datasource.demo;

import com.demo.springboot.springboot.dynamic.datasource.demo.model.User;
import com.demo.springboot.springboot.dynamic.datasource.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDynamicDatasourceDemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {

		User aa = new User().setUsername("AA").setPassword("aa").setGender(0).setAge(20);
		User bb = new User().setUsername("BB").setPassword("bb").setGender(1).setAge(21);
		User cc = new User().setUsername("CC").setPassword("cc").setGender(2).setAge(22);
		User dd = new User().setUsername("DD").setPassword("dd").setGender(2).setAge(22);
        this.userService.insertUser(aa);
        this.userService.insertUser2(bb);
        this.userService.insertUser3(cc);
        this.userService.insertUser4(dd);
    }

}
