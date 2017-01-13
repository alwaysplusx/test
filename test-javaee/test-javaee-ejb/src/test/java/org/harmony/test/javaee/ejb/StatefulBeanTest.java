package org.harmony.test.javaee.ejb;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;

import org.harmony.tes.test.Naming;
import org.harmony.tes.test.runner.OpenEJBRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author wuxii@foxmail.com
 */
@RunWith(OpenEJBRunner.class)
public class StatefulBeanTest {

    @EJB
    private BarRemote bar;
    
    @Naming
    private Context context;
    
    @Test
    public void testStatelessBean() throws NamingException, InterruptedException {
        final int count = 10;
        final CountDownLatch lock = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        StatefulBeanTest holder = new StatefulBeanTest();
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
        // count down 20 + BarBeanTest.bar
        assertEquals(21, BarBean.barBeanCount.get());
    }

}
