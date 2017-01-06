package org.harmony.test.javaee.ejb;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class BarBeanTest {

    private static EJBContainer container;
    private static Context context;

    @EJB
    private BarRemote bar;

    @BeforeClass
    public static void beforeClass() {
        container = EJBContainer.createEJBContainer();
        context = container.getContext();
    }

    @Test
    public void testStatelessBean() throws NamingException, InterruptedException {
        final int count = 10;
        final CountDownLatch lock = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        BarBeanTest holder = new BarBeanTest();
                        context.bind("inject", holder);
                        int first = holder.bar.getAndIncrement();
                        context.bind("inject", holder);
                        int second = holder.bar.getAndIncrement();
                        System.out.println(Thread.currentThread().getName() + ", first get:" + first + ", second get:" + second);

                    } catch (NamingException e) {
                    }
                    lock.countDown();
                }
            }).start();
        }

        lock.await();
        System.out.println("finish count down");
        assertEquals(20, BarBean.barBeanCount.get());
    }

}
