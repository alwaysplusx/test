package org.harmony.test.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/**
 * @author wuxii@foxmail.com
 */
public class ConcurrentTest {

    private static final long begin = 0;
    private static final long end = 200000;

    @Test
    public void testInSignleThread() {
        List<Long> primes = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (long i = begin; i < end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        System.out.println("signle thread: " + (System.currentTimeMillis() - startTime));
        System.out.println("result size: " + primes.size() + ", primes: ");
    }

    @Test
    public void testInMultiThread() throws Exception {
        final List<Long> primes = new CopyOnWriteArrayList<>();
        List<Thread> threads = new ArrayList<>();

        long threadNum = 4;
        long chunk = (end - begin) / threadNum;
        long startTime = System.currentTimeMillis();

        for (long i = 0; i < threadNum; i++) {
            final long s = begin + i * chunk;
            final long e = s + chunk;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (long j = s; j < e; j++) {
                        if (isPrime(j)) {
                            primes.add(j);
                        }
                    }
                }
            });

            threads.add(thread);
            thread.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println("multi thread: " + (System.currentTimeMillis() - startTime));
        Collections.sort(primes);
        System.out.println("result size: " + primes.size() + ", primes: ");
    }

    private static boolean isPrime(long number) {
        if (number == 2)
            return true;
        if (number == 0 || number == 1)
            return false;
        for (int i = 2; i < number /* i <= Math.sqrt(number) */; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

}
