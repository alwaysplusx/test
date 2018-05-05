package org.harmony.test.reference;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class ReferenceQueueTest {

    @Test
    public void test() throws InterruptedException {
        Object o = new Object();
        // reference可用于监控o何时被回收
        ReferenceQueue<Object> rq = new ReferenceQueue<>();
        WeakReference<Object> ref = new WeakReference<Object>(o, rq);

        assertFalse(ref.isEnqueued());
        assertNull(rq.poll());

        o = null;
        System.gc();

        Thread.sleep(100);
        assertTrue(ref.isEnqueued());
        assertNotNull(rq.poll());
    }

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        // 可用reference queue感知对象何时被回收
        ReferenceQueue<Object> rq = new ReferenceQueue<>();
        WeakReference<Object> ref = new WeakReference<Object>(o, rq);

        System.out.println("get object from weak referebce " + ref.get());

        new Thread(() -> {
            while (true) {
                Reference<? extends Object> r = rq.poll();
                if (r != null) {
                    System.out.println(r + " is been recycling");
                    break;
                }
            }
            System.out.println("termination thread run");
        }).start();

        o = null;
        System.gc();

        Thread.sleep(100);
        System.exit(0);
    }

}
