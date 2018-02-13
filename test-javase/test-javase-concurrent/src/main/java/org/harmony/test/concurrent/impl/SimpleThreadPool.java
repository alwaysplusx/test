package org.harmony.test.concurrent.impl;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

import org.harmony.test.concurrent.ThreadFactory;
import org.harmony.test.concurrent.ThreadPool;

public class SimpleThreadPool implements ThreadPool {

    protected HashSet<Worker> workers = new HashSet<Worker>();
    protected AtomicInteger c = new AtomicInteger(0);
    protected BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
    protected ReentrantLock mainLock = new ReentrantLock();
    protected int poolSize;
    protected int maxPoolSize;
    protected ThreadFactory threadFactory;

    public SimpleThreadPool(int poolSize, String threadNamePrefix) {
        this.poolSize = poolSize;
        this.maxPoolSize = poolSize;
        this.threadFactory = new ThreadFactory(threadNamePrefix);
    }

    @Override
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        int s = c.get();
        if (s < poolSize) {
            // 未必达到线程池上限则添加一个工作线程
            addWorker(command);
            s = c.get();
            return;
        }
        // 如果已经达到上限则添加到工作队列中,等待工作线程执行任务
        workQueue.offer(command);
    }

    public void offer(Runnable command) {
        workQueue.offer(command);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        RunnableFuture<T> future = newTaskFor(task, result);
        execute(future);
        return future;
    }

    @Override
    public void shutdown() {
        /*
         * final ReentrantLock mainLock = this.mainLock; mainLock.lock(); try {
         * for (Worker w : workers) { w.thread.interrupt(); } } finally {
         * mainLock.unlock(); }
         */
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<T>(runnable, value);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable);
    }

    protected boolean addWorker(Runnable task) {
        if (task == null || !workQueue.isEmpty()) {
            return false;
        }
        int s = c.get();
        if (s >= poolSize) {
            return false;
        }
        // 先增加工作线程数量
        c.compareAndSet(s, s + 1);

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker worker = null;
        try {
            final ReentrantLock mainLock = this.mainLock;
            worker = new Worker(task);
            final Thread t = worker.thread;
            try {
                if (t != null) {
                    mainLock.lock();
                    workers.add(worker);
                    workerAdded = true;
                }
            } finally {
                mainLock.unlock();
            }

            if (workerAdded) {
                t.start();
                workerStarted = true;
            }
        } finally {
            if (!workerStarted) {
                System.out.println("can't start");
            }
        }
        return workerStarted;
    }

    private void runWorker(Worker w) {
        Runnable task = w.task;
        w.task = null;
        w.unlock();
        // 如果当前任务为空则去任务队列中获取
        while (task != null || (task = getTask()) != null) {
            // 获取到任务执行锁定lock
            w.lock();
            try {
                task.run();
            } finally {
                // unlock
                task = null;
                w.unlock();
            }
        }
    }

    private Runnable getTask() {
        Runnable task = null;
        try {
            // 使用阻塞队列来使线程等待获取任务
            task = workQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return task;
    }

    class Worker extends AbstractQueuedSynchronizer implements Runnable {

        private static final long serialVersionUID = 1L;
        protected Runnable task;
        protected Thread thread;

        Worker(Runnable task) {
            this.task = task;
            this.thread = threadFactory.newThread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }

        private void lock() {
            acquire(1);
        }

        private void unlock() {
            release(1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
    }

}
