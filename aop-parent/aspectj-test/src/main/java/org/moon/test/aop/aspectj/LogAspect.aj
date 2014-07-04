package org.moon.test.aop.aspectj;

import static org.junit.Assert.*;

import org.aspectj.lang.JoinPoint;

public aspect LogAspect {

	pointcut bankMethods() : execution (* Bank.*(..));

	before(): bankMethods(){
		assertTrue(thisJoinPoint instanceof JoinPoint);
		log("before execute method " + thisJoinPoint.getSignature().getName());
	}

	after() : bankMethods(){
		log("after execute method " + thisJoinPoint.getSignature().getName());
	}
	
	protected static void log(String message) {
		System.out.println(message);
	}

}
