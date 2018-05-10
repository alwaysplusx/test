package org.harmony.test.javaee.interceptor;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
@Stateless
public class BarService implements Service {

    private static final Logger log = LoggerFactory.getLogger(BarService.class);

    @Log
    @Override
    public String sayHi(String name) {
        log.info("bar sayHi execute");
        return "Hi " + name;
    }
}
