package org.moon.ii.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactory implements java.util.concurrent.ThreadFactory {

    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public ThreadFactory(String prefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = prefix.endsWith("-") ? prefix : prefix + "-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactory("Slfe");
        for (int i = 0; i < 10; i++) {
            factory.newThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }
    }
}
