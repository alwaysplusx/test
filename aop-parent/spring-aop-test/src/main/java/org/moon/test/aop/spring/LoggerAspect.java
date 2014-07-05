package org.moon.test.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ��־����.
 * @author wux
 */
@Aspect
@Service
public class LoggerAspect {

	private Logger log = LoggerFactory.getLogger("CommonLogger");

	/**
	 * �������־�е�.
	 */
	@Pointcut("execution(* org.moon.test.aop.spring.*.deposit(..))")
	public void bankMethods() {

	}

	/**
	 * �е�ִ��ǰ����.
	 * @param jp ���ӵ�
	 * @param account �˺�
	 */
	@Before(value = "bankMethods() && args(account, *)")
	public void before(JoinPoint jp, Account account) {
		log.info("execute method " + jp.getSignature().getName() + ", account is " + account);
	}

}
