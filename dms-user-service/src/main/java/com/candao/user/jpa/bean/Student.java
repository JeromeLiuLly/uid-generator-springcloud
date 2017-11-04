package com.candao.user.jpa.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.candao.dms.uid.contants.ConstContants;

/**
 * 
 * 
 * @author jeromeLiu
 * @version 1.0.0 2017年5月12日 下午3:19:27
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5178379746958791968L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name="idGenerator", strategy="com.candao.dms.jpa.assigned.AssignedSequenceGenerator", parameters={@Parameter(name = "serverPrefix", value=ConstContants.STORE_STOP)})
	private String id;
	
	@Column(nullable = false, name = "name")
	private String name;
	
	@Column(nullable = false, name = "age")
	private Integer age;
	
	@Column(nullable = false, name = "high")
	private Integer high;
	
	public Student() {}

	public Student(String id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHigh() {
		return high;
	}

	public void setHigh(Integer high) {
		this.high = high;
	}
}
