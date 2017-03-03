package com.troy.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Triangle  {
	private String type;
	private int height;
	protected ApplicationContext context = null;
	protected String nameBean = null;
	private String name;
	
	public Triangle(String type) {
		this.type = type;
	}
	
	public Triangle(int height) {
		this.height = height;
	}
	
	public Triangle(String type, int height) {
		this.type = type;
		this.height = height;
	}
	
	public Triangle() {
		
	}
	public void draw() {
		System.out.println((this.type!=null?this.type + " triangle drawn. ":"Triangle type was not set. ") + "The height is " + height + ".");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
//	@Override
//	public void setApplicationContext(ApplicationContext context) throws BeansException {
//		this.context = context;
//		
//	}
//
//	@Override
//	public void setBeanName(String nameBean) {
//		this.nameBean = nameBean;
//		
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		//System.out.println("InitializingBean init method called for " + nameBean);
//		
//	}
//
//	@Override
//	public void destroy() throws Exception {
//		//System.out.println("Destroying beans for " + nameBean);
//		
//	}
	
	public void myInit() {
		//System.out.println("InitializingBean init method called for " + nameBean);
	}
	
	public void myDestroy() {
		//System.out.println("Destroying beans for " + nameBean);
	}

	public String getName() {
		System.out.println(name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
