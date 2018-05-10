package org.harmony.test.aop.spring;

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

    private static final Logger log = LoggerFactory.getLogger("CommonLogger");

    @Pointcut("execution(* org.harmony.test.aop.spring.*.deposit(..))")
    public void bankMethods() {

    }

    @Before(value = "bankMethods() && args(account, *)")
    public void before(JoinPoint jp, Account account) {
        log.info("execute method " + jp.getSignature().getName() + ", account is " + account);
    }

    @Before("@annotation(org.harmony.test.aop.spring.Loggable)")
    public void logging() {
        log.info("annotation logging called");
    }

}
