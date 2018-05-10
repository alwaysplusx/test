package com.company;

import javax.ejb.Remote;

/**
 * @author wuxii@foxmail.com
 */
@Remote
public interface FooRemote {

    String sayHi(String name);

}
