package com.troy.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class DisplayNameBeanPostProcessor implements BeanPostProcessor, BeanFactoryPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String nameBean)
			throws BeansException {
		// System.out.println("Before initializing bean: " + nameBean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String nameBean)
			throws BeansException {
		// System.out.println("After initializing bean: " + nameBean);
		return bean;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		//System.out.println(beanFactory.getBean("triangle-alias").toString());
		//System.out.println("Bean Factory Post Processing");
		
	}

}
