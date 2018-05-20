package org.harmony.test.dist.lock;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.ZooKeeper;
import org.harmony.test.lock.dist.ZookeeperDistributedLock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
public class ZookeeperDistributedLockTest {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperDistributedLockTest.class);
    private ZooKeeper zookeeper;
    private int count = 5;

    @Before
    public void setup() throws IOException {
        zookeeper = new ZooKeeper("localhost:2181", (int) TimeUnit.MINUTES.toMillis(30), (e) -> {
            System.out.println(e);
        });
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(count);
        CountDownLatch end = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                ZookeeperDistributedLock lock = ZookeeperDistributedLock.create(zookeeper, "foo");
                try {
                    start.await();
                    lock.lock();
                    log.info("{} get lock", name);
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        log.error("", e);
                    }
                } catch (Exception e) {
                    log.error("", e);
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

    @Test
    public void findNodes() throws Exception {
        List<String> nodes = zookeeper.getChildren("/lock", false);
        System.out.println(nodes);
    }

    @After
    public void tearDown() throws InterruptedException {
        zookeeper.close();
    }
}
