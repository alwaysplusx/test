package org.harmony.test.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class ReferenceTest {

    @Test
    public void strongTest() {
        Object o = new Object();
        // strong reference, System.gc()将不会通过GC回收
        Object ref = o;

        assertEquals(o, ref);

        o = null;
        System.gc();

        assertNotNull(ref);
    }

    @Test
    public void softTest() {
        Object o = new Object();
        // soft reference, 内存不足时（介于OOM前）才被回收
        SoftReference<Object> ref = new SoftReference<Object>(o);

        assertEquals(o, ref.get());

        o = null;
        System.gc();

        assertNotNull(ref);
    }

    @Test
    public void weakTest() {
        Object o = new Object();
        // weak reference, System.gc()将回收
        WeakReference<Object> ref = new WeakReference<Object>(o);

        assertEquals(o, ref.get());

        o = null;
        System.gc();

        assertNull(ref.get());
    }

    @Test
    public void weakHashMap() throws InterruptedException {
        Object v = new Object();
        Object k = new Object();

        WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(k, v);
        assertEquals(v, weakHashMap.get(k));

        k = null;
        System.gc();

        // System.out.println(weakHashMap.size());
        // 等待weakHashMap中的entries进入referenceQueue
        Thread.sleep(100);
        assertFalse(weakHashMap.containsValue(v));
    }

    @Test
    public void phantomTest() {
        Object o = new Object();
        // phantom.get() always return null
        PhantomReference<Object> ref = new PhantomReference<Object>(o, new ReferenceQueue<>());

        assertNull(ref.get());
        o = null;

        System.gc();
        assertNull(ref.get());
    }

}
