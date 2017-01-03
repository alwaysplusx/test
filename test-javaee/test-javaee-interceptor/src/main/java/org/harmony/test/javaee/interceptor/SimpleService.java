package org.harmony.test.javaee.interceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.harmony.test.javaee.interceptor.interceptor.LoggingInterceptor;
import org.harmony.test.javaee.interceptor.interceptorbinding.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class SimpleService {

    private String text = "abc";
    static Logger log = LoggerFactory.getLogger(SimpleService.class);

    @Interceptors({ LoggingInterceptor.class })
    public void execute() {
        log.info("execute");
    }

    @Log
    public void executeWithAnnotation() {
        log.info("execute with annotation");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
