package org.harmony.test.javaee.interceptor;

import javax.ejb.Stateless;

/**
 * @author wuxii@foxmail.com
 */
@Stateless
public class BazService implements Service {

    @Override
    public String sayHi(String name) {
        return null;
    }

}
