package org.harmony.test.java.concurrent.thread;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author wux
 *
 */
public class ThreadLocalTest {

    final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    final String var = new String("variable");

    /**
     * 该例子说明，一般情况下ThreadLocal.set()到线程中的对象时该线程自己所使用的对象，其他线程是无法访问的。
     * <p>
     * 但是ThreadLocal对象的隔离并不是通过ThreadLocal.set()来实现的。
     * <p>
     * 如果使用ThreadLocal.set()设置的对象本身为一个多线程访问对象，那么取到的还是一个被线程共享的对象
     */
    @Test
    public void testThreadLocalInShareObject() {
        threadLocal.set(var);
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(var);
                assertEquals(var, threadLocal.get());
                assertTrue(var == threadLocal.get());
            }
        }).start();
    }

    @Test
    public void testThreadLocal() {
        threadLocal.set(var);
        new Thread(new Runnable() {
            @Override
            public void run() {
                assertNull(threadLocal.get());
            }
        }).start();
    }

    @Test
    public void testThreadLocalDiffThread() {
        threadLocal.set("Var-main");
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Var-A");
                PrintVar.print();
            }
        }, "Thread-A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Var-B");
                PrintVar.print();
            }
        }, "Thread-B").start();
    }

    static class PrintVar {
        static void print() {
            System.out.println(Thread.currentThread().getName() + " get var is " + threadLocal.get());
        }
    }

}
