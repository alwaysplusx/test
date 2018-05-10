package org.harmony.test.aop.proxy;

import static org.junit.Assert.*;

import org.harmony.test.aop.proxy.ServiceProxy;
import org.harmony.test.aop.proxy.SimpleUserService;
import org.harmony.test.aop.proxy.UserService;
import org.junit.Test;

public class UserServiceTest {

    public static void main(String[] args) {
        UserService service = (UserService) ServiceProxy.newInstance(new SimpleUserService());
        service.sayHi("World");
    }

    @Test
    public void test() {
        UserService service = (UserService) ServiceProxy.newInstance(new SimpleUserService());
        String message = service.sayHi("World");
        assertEquals("Hi World", message);
    }
}
