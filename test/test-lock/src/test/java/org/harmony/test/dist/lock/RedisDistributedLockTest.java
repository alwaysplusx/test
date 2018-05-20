package org.harmony.test.dist.lock;

import java.util.concurrent.CountDownLatch;

import org.harmony.test.lock.dist.RedisDistributedLock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wuxii@foxmail.com
 */
public class RedisDistributedLockTest {

    private static final Logger log = LoggerFactory.getLogger(RedisDistributedLockTest.class);

    private JedisPool jedisPool;
    private int count = 100;

    @Before
    public void setup() {
        JedisPoolConfig cfg = new JedisPoolConfig();
        jedisPool = new JedisPool(cfg, "192.168.110.128", 6379, 1000 * 60, "123456");
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(count);
        CountDownLatch end = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                RedisDistributedLock lock = RedisDistributedLock.create(jedisPool, "foo");

                try {
                    start.await();
                    lock.lock();
                    log.info("{} get lock", name);
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        log.error("", e);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    log.info("{} release lock", name);
                }

                end.countDown();
            }).start();
            start.countDown();
        }

        end.await();

    }

}
