package com.troy.spring.test;

import java.sql.Timestamp;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent appEvent) {
		long ts = appEvent.getTimestamp();
		System.out.println(appEvent.toString() + " [" + new Timestamp(ts) + "]");
	}

}
