package org.harmony.test.timer.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTimerTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("SimpleJobDetail").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        Thread.sleep(1000 * 10);
        scheduler.shutdown();
    }
}
