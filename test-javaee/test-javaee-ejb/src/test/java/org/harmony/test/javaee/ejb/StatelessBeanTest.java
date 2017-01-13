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
public class StatelessBeanTest {

    private static EJBContainer container;
    private static Context context;

    @EJB
    private FooRemote foo;

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
                        // 前后绑定的会话Bean应为是无状态的, 所以其前后的两次值具有随机性
                        // 在并发多的情况下前后的值就无法保证顺序性
                        final StatelessBeanTest holder = new StatelessBeanTest();
                        context.bind("inject", holder);
                        int first = holder.foo.getAndIncrement();
                        // 第二次
                        context.bind("inject", holder);
                        int second = holder.foo.getAndIncrement();
                        System.out.println(Thread.currentThread().getName() + ", first get:" + first + ", second get:" + second);

                    } catch (NamingException e) {
                    }
                    lock.countDown();
                }
            }).start();
        }

        lock.await();
        System.out.println("finish count down");
        assertNotEquals(20, FooBean.fooBeanCount.get());
    }

}
