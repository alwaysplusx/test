package org.harmony.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutorsTest {

    static Logger log = LoggerFactory.getLogger(Executors.class);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // 缓冲线程池，线程数量不定
        // Executors.newCachedThreadPool();
        // 单线程线程池，线程停止后会自动启动一个线程代替
        // Executors.newSingleThreadExecutor();
        while (true) {
            // execute with pool thread
            threadPool.execute(new MyTask());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
         * while(true){ //create a new thread every time new MyThread().start();
         * try { Thread.sleep(1000); } catch (InterruptedException e) {
         * e.printStackTrace(); } }
         */
    }

    @Test
    public void test() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                throw new RuntimeException();
            });
        } catch (Exception e) {
        }

        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("run");
        });

        executor.shutdown();
    }

    @Test
    public void test2() {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        try {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                throw new RuntimeException();
            });
        } catch (Exception e) {
        }

        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("run");
        });

        executor.shutdown();
    }
}

class MyThread extends Thread {

    static Logger log = LoggerFactory.getLogger(MyThread.class);

    @Override
    public void run() {
        log.info("has run");
    }
}

class MyTask implements Runnable {

    static int loop = 0;

    static Logger log = LoggerFactory.getLogger(MyTask.class);

    @Override
    public void run() {
        log.info("has run " + (loop++));
    }

}
