package org.harmony.test.concurrent.thread;

/**
 * 线程中的互斥测试类
 * <p>
 * {@link ThreadSynchronizedTest.Output#println(String)}只能当一个线程打印完所有字符后才能由别的线程打印，或当前线程继续打印字符串
 * 
 * @author wux
 */
public class ThreadSynchronizedTest {

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

        public void print(char c) {
            System.out.print(c);
        }

        /**
         * 互斥线程打印一个字符串
         * 
         * @param message
         */
        public void println(String message) {
            synchronized (ThreadSynchronizedTest.Output.class) {
                System.out.print(Thread.currentThread().getName() + ":");
                for (int i = 0; i < message.length(); i++) {
                    print(message.charAt(i));
                }
                System.out.println();
            }
        }
    }
}
