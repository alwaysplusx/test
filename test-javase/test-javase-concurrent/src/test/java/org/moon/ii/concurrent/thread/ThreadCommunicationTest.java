package org.moon.ii.concurrent.thread;

/**
 * 线程中的通信，当一个线程打印完后只能等待别的线程打印后才能接着打印，不能同个线程连续打印两次
 * @author wux
 *
 */
public class ThreadCommunicationTest {

    public static void main(String[] args) {

        final Output output = new Output();

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
        }, "Print-Thread-A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    output.println("OOO");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Print-Thread-B").start();

    }

    static class Output {

        private String currentThreadName = new String();

        public void print(char c) {
            System.out.print(c);
        }

        public synchronized void println(String message) {
            while (currentThreadName.equals(Thread.currentThread().getName())) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentThreadName = Thread.currentThread().getName();
            System.out.print(Thread.currentThread().getName() + ":");
            for (int i = 0; i < message.length(); i++) {
                print(message.charAt(i));
            }
            System.out.println();
            this.notify();
        }

    }
}
