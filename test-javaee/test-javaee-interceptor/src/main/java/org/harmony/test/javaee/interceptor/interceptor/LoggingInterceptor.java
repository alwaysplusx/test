package org.harmony.test.javaee.interceptor.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.harmony.test.javaee.interceptor.interceptorbinding.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Log
public class LoggingInterceptor {

    static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        log.info("execute before");
        // Object target = ctx.getTarget();
        // Field[] fields = target.getClass().getDeclaredFields();
        // Field textField = null;
        // for (Field f : fields) {
        // if (f.getType() == String.class) {
        // textField = f;
        // }
        // }
        // if (!textField.isAccessible()) {
        // textField.setAccessible(true);
        // }
        // String text = (String) textField.get(target);
        Object result = ctx.proceed();
        log.info("execute after");
        return result;
    }
}
