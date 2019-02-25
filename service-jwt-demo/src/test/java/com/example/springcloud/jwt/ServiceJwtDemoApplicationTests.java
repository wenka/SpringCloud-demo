package com.example.springcloud.jwt;

import com.easywork.wx.model.resp.AccessTokenResp;
import com.easywork.wx.service.CgiBinService;
import com.easywork.wx.service.SnsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceJwtDemoApplicationTests {

	@Autowired
	private CgiBinService cgiBinService;

	@Test
	public void contextLoads() {
        AccessTokenResp accessToken =
                cgiBinService.getAccessToken();
        System.out.println(accessToken);
    }

}

