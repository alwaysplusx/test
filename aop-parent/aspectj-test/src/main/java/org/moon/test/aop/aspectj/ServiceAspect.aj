package org.moon.test.aop.aspectj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect ServiceAspect {

	private Logger log = LoggerFactory.getLogger(ServiceAspect.class);
	
	pointcut serviceMethods() : execution(* org.moon.test.aop.aspectj..*(..));
	
    before() : serviceMethods() {
    	log.debug("before execut method");
    }
	
}
