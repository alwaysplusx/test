package org.harmony.test.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.harmony.test.concurrent.ThreadFactory;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), new ThreadFactory("slef-turning"));
        for(;;){
            threadPool.submit(new Task());
            Thread.sleep(200);
        }
        //threadPool.shutdown();
    }
    
    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());            
        }
    }
}
