package com.company;

import javax.ejb.Stateless;

/**
 * @author wuxii@foxmail.com
 */
@Stateless(mappedName = "FooBean")
public class FooBean implements FooRemote, FooLocal {

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

}
