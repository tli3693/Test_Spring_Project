package com.troy.spring.test;

import org.springframework.context.ApplicationEvent;

public class DrawEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 86030248855558757L;

	public DrawEvent(Object source) {
		
		super(source);
		
	}
	
	public String toString() {
		return "Draw event has occurred!";
		
	}

}
