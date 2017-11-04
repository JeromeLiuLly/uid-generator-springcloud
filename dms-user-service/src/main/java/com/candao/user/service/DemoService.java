package com.candao.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.candao.dms.uid.contants.ConstContants;
import com.candao.dms.uid.service.UIDService;
import com.candao.user.jpa.bean.Student;
import com.candao.user.jpa.repository.primary.StudentRepository;

@Component
public class DemoService {
	
	@Autowired
	private UIDService uIDService;
	
	@Autowired
	private StudentRepository studentRepository;

	public String getUidByOne() {
		String uid = uIDService.getUid(ConstContants.MCDONALD_ORDER);
		return uid;
	}
	
	@Transactional
	public void save(Student student){
		studentRepository.saveAndFlush(student);
	}

	public String getUidWithCache(){
		String uid = uIDService.getUidWithCache(ConstContants.MCDONALD_ORDER);
		return uid;
	}
	
	public String getUidWithCache2(){
		String uid = uIDService.getUidWithCache(ConstContants.KUNGFU_ORDER);
		return uid;
	}

}
