package com.troy.spring.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.troy.spring.test.data.entity.User;

public class TestMain {

  public static void main(String[] args) {
	  ApplicationContext ctx = new ClassPathXmlApplicationContext("resources/spring-aop.xml");

	  
	  ShapeService shapeService = ctx.getBean("shapeService", ShapeService.class);
	  shapeService.getCircle().getName();
	  shapeService.getTriangle().getName();
	  shapeService.getCircle().setName("TEST");
  }
}