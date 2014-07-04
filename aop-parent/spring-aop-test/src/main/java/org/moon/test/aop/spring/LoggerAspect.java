package org.moon.test.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggerAspect {

	@Pointcut("execution(* *.*(..))")
	public void bankMethods() {

	}

	@Before("bankMethods()")
	public void before(JoinPoint jp) {
		System.err.println("======> " + jp.getSignature());
	}

}
