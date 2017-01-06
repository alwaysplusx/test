package org.harmony.test.javaee.ejb;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Singleton;

/**
 * @author wuxii@foxmail.com
 */
@Singleton
public class BazBean implements BazRemote {

    private AtomicInteger count = new AtomicInteger();

    public static final AtomicInteger bazBeanCount = new AtomicInteger();

    public BazBean() {
        bazBeanCount.incrementAndGet();
    }

    @Override
    public int getAndIncrement() {
        return count.getAndIncrement();
    }

}
