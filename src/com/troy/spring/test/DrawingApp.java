package com.troy.spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	public static void main(String[] args) {
		AbstractApplicationContext context = null;
		try {
			 context = new ClassPathXmlApplicationContext("resources/spring.xml");
			// context.registerShutdownHook(); // To garbage collect/destroy objects at end of runtime

			Shape circle = (Shape) context.getBean("circle-alias");
			circle.draw();
			
			//String msg = context.getMessage("greeting", new Object[]{}, "Default Greeting", new Locale("en-us"));
			//System.out.println(msg);
			
		} finally { // close context after execution
			if (context != null)
				context.close();
		}
	}

}
