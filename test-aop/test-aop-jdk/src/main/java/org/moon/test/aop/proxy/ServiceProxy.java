package org.moon.test.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceProxy implements InvocationHandler {

    private Logger log = LoggerFactory.getLogger(ServiceProxy.class);
    private Object target;

    private ServiceProxy(Object target) {
        this.target = target;
    }

    public static Object newInstance(Object obj) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ServiceProxy.class.getClassLoader();
        return Proxy.newProxyInstance(loader, obj.getClass().getInterfaces(), new ServiceProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // before method point cut
        log.debug("before execut method " + method.getName());
        Object result = null;
        try {
            result = method.invoke(target, args);
            // after method point cut
            log.debug("after execut method " + method.getName());
        } catch (RuntimeException e) {
            // method exception point cut
            log.error("method " + method.getName() + " throw exception" + e);
            throw e;
        }
        return result;
    }

}
