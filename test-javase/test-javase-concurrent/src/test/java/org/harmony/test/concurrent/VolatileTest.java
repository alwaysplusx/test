package org.harmony.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {

    public int inc0 = 0;
    public volatile int inc1 = 0;

    public int inc2 = 0;
    public int inc3 = 0;

    ReentrantLock lock = new ReentrantLock();

    public void inc0() {
        inc0++;
        inc1++;
    }

    public synchronized void inc2() {
        inc2++;
    }

    public void inc3() {
        lock.lock();
        try {
            inc3++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.inc0();
                    test.inc2();
                    test.inc3();
                }
            }).start();
        }

        while (Thread.activeCount() > 1) // 保证前面的线程都执行完
            Thread.yield();

        System.out.println(test.inc0);
        System.out.println(test.inc1);
        System.out.println(test.inc2);
        System.out.println(test.inc3);
    }
}