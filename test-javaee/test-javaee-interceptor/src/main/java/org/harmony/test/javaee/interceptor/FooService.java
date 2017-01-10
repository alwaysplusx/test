package org.harmony.test.javaee.interceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class FooService implements Service {

    private static final Logger log = LoggerFactory.getLogger(FooService.class);

    @Override
    @Interceptors({ LoggingInterceptor.class })
    public String sayHi(String name) {
        log.info("foo sayHi execute");
        return "Hi " + name;
    }

}
