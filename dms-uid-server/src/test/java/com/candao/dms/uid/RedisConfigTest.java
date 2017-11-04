package com.candao.dms.uid;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.candao.dms.uid.contants.ConstContants;
import com.candao.dms.uid.service.UIDService;

/**
 * @author jeromeLiu
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DmsUidServiceApplication.class)
public class RedisConfigTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private UIDService uIDService;

	// @Test
	public void testSave() {
		stringRedisTemplate.opsForValue().set("123", "123");
		System.out.println(UUID.randomUUID().toString().length());
	}

	// @Test
	public void testuid() {
		String uid = uIDService.getUid(ConstContants.MCDONALD_ORDER);
		System.out.println(uIDService.getUid(ConstContants.MCDONALD_ORDER) + " ## " + uid.length());
	}

	@Test
	public void testThreadWithUID() throws InterruptedException {
		ExecutorService extensions = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			extensions.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(uIDService.getUidWithCache(ConstContants.MCDONALD_ORDER));
				}
			});
		}
		Thread.sleep(5000);
	}

}
