package com.troy.spring.test.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {


	@Before("circlePointcut()")
	public void circleAdvice(JoinPoint joinPoint) {
		System.out.println(joinPoint.getTarget() + "." + joinPoint.getSignature().getName() + " method run.");
	}
	
	@Before("args(name)")
	public void stringArgumentMethods(String name) {
		System.out.println("A method that takes String arguments has been called");
	}
	@Pointcut("within(com.troy.spring.test.Circle)")
	public void circlePointcut() {}	
	@Pointcut("execution(* *get*())")
	public void allGetters() {}
	
}
