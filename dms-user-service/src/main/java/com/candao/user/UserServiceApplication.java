package com.candao.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.candao"}) 
public class UserServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(UserServiceApplication.class).web(true).run(args);
	}
	
}
