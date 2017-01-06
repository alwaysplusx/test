package org.harmony.test.javaee.ejb;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ejb.Stateful;

/**
 * @author wuxii@foxmail.com
 */
@Stateful
public class BarBean implements BarRemote {

    private AtomicInteger count = new AtomicInteger();

    public static final AtomicInteger barBeanCount = new AtomicInteger();

    public BarBean() {
        barBeanCount.incrementAndGet();
    }

    @Override
    public int getAndIncrement() {
        return count.getAndIncrement();
    }
}
