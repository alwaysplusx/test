package org.harmony.test.redis;

import java.util.concurrent.CountDownLatch;

import org.junit.After;

/**
 * @author wuxii@foxmail.com
 */

import org.junit.Before;
import org.junit.Test;

public class RushBuyingTest {

    private RushService rushService;

    private int rushCount = 1000;

    @Before
    public void setup() {
        rushService = new RushService();
        rushService.init(10);
    }

    @Test
    public void testRush() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(rushCount);
        CountDownLatch end = new CountDownLatch(rushCount);

        for (int i = 0; i < rushCount; i++) {
            new Thread(() -> {
                start.countDown();

                rushService.rush(Thread.currentThread().getName());

                end.countDown();
            }).start();

            start.countDown();
        }

        end.await();
    }

    @After
    public void tearDown() {
        rushService.destroy();
    }

}
