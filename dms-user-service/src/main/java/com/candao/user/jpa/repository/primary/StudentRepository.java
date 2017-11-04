package com.candao.user.jpa.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candao.user.jpa.bean.Student;

/**
 * 
 * 
 * @author xxx
 * @version 1.0.0 2017年5月12日 下午3:22:06
 */
public interface StudentRepository extends JpaRepository<Student,String> {

}
