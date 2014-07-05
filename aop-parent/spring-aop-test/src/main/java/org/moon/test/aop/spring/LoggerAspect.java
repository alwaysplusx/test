package org.moon.test.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 日志切面.
 * @author wux
 */
@Aspect
@Service
public class LoggerAspect {

	private Logger log = LoggerFactory.getLogger("CommonLogger");

	/**
	 * 定义的日志切点.
	 */
	@Pointcut("execution(* org.moon.test.aop.spring.*.deposit(..))")
	public void bankMethods() {

	}

	/**
	 * 切点执行前方法.
	 * @param jp
	 *            连接点
	 */
	@Before(value = "bankMethods()")
	public void before(JoinPoint jp) {
		log.info("execute method " + jp.getSignature().getName());
	}

}
