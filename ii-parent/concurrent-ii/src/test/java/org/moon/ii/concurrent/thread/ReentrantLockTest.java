package org.moon.ii.concurrent.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {

        final ThreadSynchronizedTest.Output output = new ThreadSynchronizedTest.Output();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    output.println("ooo");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Print-Thread-A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    output.println("---");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Print-Thread-B").start();

    }

    /**
     * 打印字符类
     * 
     * @author wux
     */
    static class Output {

        private Lock lock = new ReentrantLock();

        public void print(char c) {
            System.out.print(c);
        }

        /**
         * 互斥线程打印一个字符串
         * 
         * @param message
         */
        public void println(String message) {
            lock.lock();
            try {
                System.out.print(Thread.currentThread().getName() + ":");
                for (int i = 0; i < message.length(); i++) {
                    print(message.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }
}
