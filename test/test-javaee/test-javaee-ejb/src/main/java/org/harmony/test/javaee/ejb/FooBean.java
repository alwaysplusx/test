package org.harmony.test.javaee.ejb;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Stateless;

/**
 * @author wuxii@foxmail.com
 */
@Stateless
public class FooBean implements FooRemote {

    private AtomicInteger count = new AtomicInteger();

    public static final AtomicInteger fooBeanCount = new AtomicInteger();

    public FooBean() {
        fooBeanCount.incrementAndGet();
    }

    @Override
    public int getAndIncrement() {
        return count.getAndIncrement();
    }

}
