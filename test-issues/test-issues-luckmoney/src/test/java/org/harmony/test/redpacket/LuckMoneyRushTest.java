package org.harmony.test.redpacket;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.harmony.test.redpacket.LuckMoneyService.LuckGuy;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuxii@foxmail.com
 */
public class LuckMoneyRushTest {

    private static final Logger log = LoggerFactory.getLogger(LuckMoneyRushTest.class);
    public static final int count = 100;
    private static CountDownLatch startLatch = new CountDownLatch(count);
    private static CountDownLatch endLatch = new CountDownLatch(count);

    private static LuckMoneyService luckMoneyService = new LuckMoneyService();
    private static Long luckMoneyId;

    @BeforeClass
    public static void setup() {
        luckMoneyId = luckMoneyService.publish(200, 20);
    }

    @Test
    public void test() throws InterruptedException {
        for (int i = count; i > 0; i--) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    log.debug("start rush redpacket money");
                    String name = Thread.currentThread().getName();
                    LuckGuy guy = luckMoneyService.rush(luckMoneyId, name);
                    if (guy != null) {
                        if (guy.getMoney() == 0) {
                            log.error("get worng luck money", guy);
                        } else {
                            log.info("get redpacket money {}", guy);
                        }
                    } else {
                        log.info("{} you are not luck guy", name);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            }).start();
            startLatch.countDown();
        }

        endLatch.await();

        List<LuckGuy> guys = luckMoneyService.getAllLuckGuys(luckMoneyId);
        guys.forEach(e -> {
            log.info("{} get luck money {}", e.getName(), e.getMoney());
        });
    }

    @Test
    public void test1() {
        System.out.println(luckMoneyService.rush(luckMoneyId, Thread.currentThread().getName()));
    }

}
