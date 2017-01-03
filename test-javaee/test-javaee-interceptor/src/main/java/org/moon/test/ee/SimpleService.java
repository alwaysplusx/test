package org.moon.test.ee;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.moon.test.ee.interceptor.LoggingInterceptor;
import org.moon.test.ee.interceptorbinding.Log;
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
