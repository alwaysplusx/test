package org.moon.test.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggerAspect {

	Logger log = LoggerFactory.getLogger("CommonLogger");

	@Pointcut("execution(* org.moon.test.aop.spring.*.deposit(..))")
	public void bankMethods() {

	}

	@Before(value = "bankMethods()")
	public void before(JoinPoint jp) {
		System.err.println("execute method " + jp.getSignature().getName());
	}

}
