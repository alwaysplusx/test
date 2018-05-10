package org.harmony.test.java.concurrent.thread;

public class ThreadTest {

    /*Thread.run(){
         //target is runnable
        if(target != null) {
            target.run()
        }
    }*/

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("==> " + Thread.currentThread().getName() + " run");
            }
        }).start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("==> " + Thread.currentThread().getName() + " run");
            }
        }.start();

    }

}
