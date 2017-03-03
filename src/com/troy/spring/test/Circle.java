package com.troy.spring.test;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Circle  {

	private Point center;
	private Point test;
	
	private String name;
	
	@Autowired
	private MessageSource messageSource;
	
	private ApplicationEventPublisher publisher;
	

	public void draw() {
		String draw = messageSource.getMessage("drawing_circle", null, "Default Greeting", null);
		System.out.println(draw);
		
		String centerMsg = messageSource.getMessage("drawing_point", new Object[]{center.getX(), center.getY()}, "Default Point", new Locale("en-us"));
		System.out.println(centerMsg);
		
		String greeting = messageSource.getMessage("greeting", null, "Default Greeting", null);
		System.out.println("Circle says: " + greeting);
		
		publisher.publishEvent(new DrawEvent(this));
		//System.out.println("Center point: (" + center.getX() + ", " + center.getY() + ")");
		//System.out.println("Test point: (" + test.getX() + ", " + test.getY() + ")");
	}

	public Point getCenter() {
		return center;
	}


	@Autowired
	@Qualifier("circleRelated")
	public void setCenter(Point center) {
		this.center = center;
	}

	public Point getTest() {
		return test;
	}

	@Resource
	public void setTest(Point test) {
		this.test = test;
	}
	
	@PostConstruct
	public void initializeCirlce() {
		System.out.println("Init Circle");
	}

	@PreDestroy
	public void destroyCirlce() {
		System.out.println("Destroy Circle");
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

//	@Override
//	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
//		this.publisher = eventPublisher;
//		
//	}

	public String getName() {
		System.out.println("TEST: " + name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Circle";
	}
}
