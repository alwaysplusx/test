package org.moon.test.collection;

import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        for (int i = 0; i < 11; i++) {
            System.out.println(i);
            queue.put(i + "");
        }
        System.out.println(queue.size());
    }

}
