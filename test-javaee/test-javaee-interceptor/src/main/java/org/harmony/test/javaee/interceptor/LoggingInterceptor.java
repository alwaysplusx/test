package org.harmony.test.javaee.interceptor;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log
@Interceptor
public class LoggingInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @EJB(beanName = "BazService")
    private Service service;

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        log.info("execute before");
        Object result = ctx.proceed();
        log.info("execute after");
        System.err.println(service);
        return result;
    }

}
