package org.moon.test.jpa.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class ObjectProxy implements InvocationHandler {

    public abstract Object bind(Object target);

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        perExecute(method);
        Object result;
        try {
            result = execute(method, args);
        } finally {
            postExecute(method);
        }
        return result;
    }

    public abstract Object execute(Method method, Object[] args) throws Throwable;

    public abstract void postExecute(Method method);

    public abstract void perExecute(Method method);

}
