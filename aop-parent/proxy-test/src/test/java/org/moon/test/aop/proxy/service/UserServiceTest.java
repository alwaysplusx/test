package org.moon.test.aop.proxy.service;

import org.moon.test.aop.proxy.ServiceProxy;

public class UserServiceTest {


    public static void main(String[] args) {
        UserService service = (UserService) ServiceProxy.newInstance(new SimpleUserService());
        service.sayHi("World");
    }
}
