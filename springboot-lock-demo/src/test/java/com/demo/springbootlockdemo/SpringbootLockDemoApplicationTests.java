package com.demo.springbootlockdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLockDemoApplicationTests {

	@Test
	public void contextLoads() {

		List<String> ids = Arrays.asList("a,b,c".split(","));
		System.out.println(ids);

		System.out.println(StringUtils.collectionToDelimitedString(ids,","));

		String[] strings = StringUtils.commaDelimitedListToStringArray("a,b,c");
		System.out.println(strings);

	}

}
