package org.harmony.test.java.concurrent;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1 + 1;
            }
        });
        new Thread(task).start();
        assertEquals(Integer.valueOf(2), task.get());
    }

}
