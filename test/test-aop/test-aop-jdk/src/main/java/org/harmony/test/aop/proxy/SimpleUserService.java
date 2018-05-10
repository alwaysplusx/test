package org.harmony.test.aop.proxy;

public class SimpleUserService implements UserService {

    @Override
    public String sayHi(String name) {
        return "Hi " + name;
    }

}
