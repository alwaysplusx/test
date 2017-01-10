package org.harmony.test.javaee.interceptor;

import javax.ejb.Remote;

/**
 * @author wuxii@foxmail.com
 */
@Remote
public interface Service {

    String sayHi(String name);

}
