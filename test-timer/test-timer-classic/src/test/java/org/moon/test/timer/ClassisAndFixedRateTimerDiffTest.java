package org.moon.test.timer;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassisAndFixedRateTimerDiffTest {

    static Logger log = LoggerFactory.getLogger(ClassisAndFixedRateTimerDiffTest.class);
    static long[] sleepTimes = new long[] { 2000, 204, 3121, 5843, 121, 3673, 965, 5751, 1900, 48, 1185, 4450, 254, 1232, 20, 3647, 576, 1022 };
    static long period = 1000 * 2;

    @Test
    @Ignore
    public void avgSleepTimes() {
        int count = 0;
        for (Long l : sleepTimes) {
            count += l;
        }
        assertEquals(2, count / sleepTimes.length);
    }

    public static void main(String[] args) throws Exception {
        Timer t1 = new Timer();
        Timer t2 = new Timer();

        // 当同一个Timer运行两个定时任务时候貌似会出现任务定时不正确
        t1.schedule(new TimerTask() {
            Date start;
            Date time = new Date();
            int i = 0;

            @Override
            public void run() {
                Date now = new Date();
                if (i == 0) {
                    start = now;
                }
                i++;
                log.debug(">>> task1  第" + i + "次运行，" + "距上次运行 " + (now.getTime() - time.getTime()) + "ms, " + "该任务总共运行" + (now.getTime() - start.getTime()) + "ms, " + "当前任务将暂停" + sleepTimes[i] + "ms");
                time = now;
                if (i == sleepTimes.length - 1) {
                    log.info(">>>>>>>>>> task1 terminated,共执行了" + i + "次，总耗时" + (now.getTime() - start.getTime()) + "ms");
                    this.cancel();
                }
                try {
                    Thread.sleep(sleepTimes[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, period);

        t2.scheduleAtFixedRate(new TimerTask() {
            Date time = new Date();
            Date start;
            int i = 0;

            @Override
            public void run() {
                Date now = new Date();
                if (i == 0) {
                    start = now;
                }
                i++;
                log.debug("### task2  第" + i + "次运行，" + "距上次运行 " + (now.getTime() - time.getTime()) + "ms, " + "该任务总共运行"
                        + (now.getTime() - start.getTime()) + "ms, 当前任务将暂停" + sleepTimes[i] + "ms");
                time = now;
                if (i == sleepTimes.length - 1) {
                    log.info("################# task2 terminated,共执行了" + i + "次，总耗时" + (now.getTime() - start.getTime()) + "ms");
                    this.cancel();
                }
                try {
                    Thread.sleep(sleepTimes[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, period);

        Thread.sleep(1000 * 60 * 3);
        t1.cancel();
        t2.cancel();
    }
}
