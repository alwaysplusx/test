/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.harmony.test.concurrent;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
@Ignore
public class WaitNotifyTest {

    private static final Object lockObject = new Object();
    private int waitTime = 1000 * 10;
    private boolean stop;
    private int deep;

    @Test
    public void test() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockObject) {
                    loop();
                    lockObject.notify();
                }
            }
        }, "Sub-Thread-loop").start();
        // main thread
        long start = System.currentTimeMillis();
        try {
            synchronized (lockObject) {
                while (!stop && System.currentTimeMillis() - start < waitTime) {
                    lockObject.notify();
                    lockObject.wait();
                    System.out.println(">>> main thread be notified");
                }
                stop = true;
                lockObject.notify();
            }
        } catch (InterruptedException e) {
        }
        // while (deep != 0) {
        // System.out.println(">>> wait sub destory");
        // Thread.sleep(1000);
        // }
        // Thread.sleep(Long.MAX_VALUE);
    }

    public void loop() {
        deep++;
        System.out.println(">> loop of " + deep);
        lockObject.notify();
        try {
            if (stop) {
                return;
            }
            if (deep > 3)
                return;
            lockObject.wait();
            System.out.println(">> sun thread be notified");
            Thread.sleep(1000);
            loop();
        } catch (Exception e) {
        } finally {
            deep--;
        }
    }

}
