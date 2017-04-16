package org.harmony.test.java.vm;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Ignore;
import org.junit.Test;

public class ClassLoaderTest {

    /**
     * 只有在类被主动加载时候才会导致类的初始化
     */
    public static void main(String[] args) {
        /*
         * 通过子类引用父类的常量,子类将不会初始化
         */
        System.out.println(SubClass.a);
        /*
         * 引用final常量将不会导致类的初始化
         */
        System.out.println(SubClass.b);
    }

    private static class SupperClass {
        public static String a = "Hello";
        public static final String b = "FINAL HELLO";
        static {
            System.out.println("SupperClass init");
            superCount.incrementAndGet();
        }
    }

    private static class SubClass extends SupperClass {
        static {
            System.out.println("SubClass init");
            subCount.incrementAndGet();
        }
    }

    private static class Foo {

        private static final String TEXT;

        static {
            TEXT = "HELLO";
            count.getAndIncrement();
        }
    }

    private static final AtomicInteger count = new AtomicInteger();
    private static final AtomicInteger superCount = new AtomicInteger();
    private static final AtomicInteger subCount = new AtomicInteger();

    @Test
    @Ignore
    public void testA() {
        // 通过子类引用父类的常量, 子类将不会初始化
        System.out.println(SubClass.a);
        assertEquals(1, superCount.get());
        assertEquals(0, subCount.get());
    }

    @Test
    @Ignore
    public void testB() {
        // 引用final常量将不会导致类的初始化
        System.out.println(SubClass.b);
        assertEquals(0, superCount.get());
        assertEquals(0, subCount.get());
    }

    @Test
    @Ignore
    public void testC() {
        // 引用计算常量将导致初始化
        System.out.println(Foo.TEXT);
        assertEquals(1, count.get());
    }

}
