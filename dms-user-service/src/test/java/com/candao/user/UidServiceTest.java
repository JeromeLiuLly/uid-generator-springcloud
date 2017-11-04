package com.candao.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.candao.user.service.DemoService;

/**
 * 
 * 
 * @author jeromeLiu
 * @version 1.0.0 2017年5月11日 上午11:42:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UidServiceTest {
	
	@Autowired
	private DemoService demoService;

	@Test
	public void testByOne(){
		System.out.println(demoService.getUidByOne());
	}
	
	@Test
	public void testByCache(){
		System.out.println(demoService.getUidWithCache());
	}
	
	@Test
	public void testByCache2(){
		System.out.println(demoService.getUidWithCache2());
	}
	
}
