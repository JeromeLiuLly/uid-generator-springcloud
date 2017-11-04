package com.candao.user;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.candao.user.jpa.bean.Student;
import com.candao.user.jpa.repository.primary.StudentRepository;
import com.candao.user.service.DemoService;

/**
 * 
 * 
 * @author jeromeLiu
 * @version 1.0.0 2017年5月11日 上午11:20:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class JpaServiceTest {


	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private DemoService demoService;
	

	@Test
	public void testSave2() {
		Student student = new Student();
		student.setName("jeromeLiu");
		student.setAge(25);
		student.setHigh(170);
		studentRepository.save(student);
	}
	
	@Test
	public void testSelect(){
		Student student = studentRepository.findOne("STORE-STOP_1000_1070995759349891075");
		System.out.println(JSONObject.toJSONString(student));
	}
	
	@Test
	public void test2(){
		List<String> list = new ArrayList<String>();
		list.add("age > ?1");
		System.out.println(StringUtils.join(list, " or "));
	}
	
}
