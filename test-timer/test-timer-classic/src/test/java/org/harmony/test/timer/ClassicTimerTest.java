package org.harmony.test.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassicTimerTest {

    static Logger log = LoggerFactory.getLogger(ClassicTimerTest.class);

    public static void main(String[] args) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        final Date date = new Date(new Date().getTime() + 1000 * 50);
        Timer timer = new Timer();

        // run once after 2 second
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("task 1 run once");
            }
        }, 1000 * 2);

        // repeatedly run every 2 second
        timer.schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                log.info("task 2 repeat run after 2 second");
                if (i++ < 10) {
                    log.info("cancel task2 after 10 times");
                    this.cancel();
                }
            }
        }, 1000 * 4, 2000);

        // specify time to run
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("task3 running at time " + sdf.format(date));
            }
        }, date);

        timer.schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                log.info("task4 first running at time " + sdf.format(date));
                if (i++ < 3) {
                    log.info("cancel task4");
                    this.cancel();
                }
            }
        }, date, 1000);
        
        log.info("exit");

        Thread.sleep(1000 * 60);
        log.info("cancel timer");
        timer.cancel();
    }

}
