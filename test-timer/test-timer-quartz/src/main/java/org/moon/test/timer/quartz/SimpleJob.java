package org.moon.test.timer.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job {

    private static Logger log = LoggerFactory.getLogger(SimpleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("execute simple job");
        //Calendar calendar = context.getCalendar();
        String instanceId = context.getFireInstanceId();
        Date fireTime = context.getFireTime();
        JobDetail jobDetail = context.getJobDetail();
        Job jobInstance = context.getJobInstance();
        long jobRunTime = context.getJobRunTime();
        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        Date nextFireTime = context.getNextFireTime();
        Date previousFireTime = context.getPreviousFireTime();
        // TriggerKey recoveringTriggerKey = context.getRecoveringTriggerKey();
        int refireCount = context.getRefireCount();
        Object result = context.getResult();
        Date scheduledFireTime = context.getScheduledFireTime();
        Scheduler scheduler = context.getScheduler();
        Trigger trigger = context.getTrigger();

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("|                        JobExecutionContext                          |");
        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|    instanceId                  " + instanceId);
        System.out.println("|    previousFireTime            " + previousFireTime);
        System.out.println("|    fireTime                    " + fireTime);
        System.out.println("|    nextFireTime                " + nextFireTime);
        System.out.println("|    jobDetail                   " + jobDetail);
        System.out.println("|    jobInstance                 " + jobInstance);
        System.out.println("|    jobRunTime                  " + jobRunTime);
        System.out.println("|    mergedJobDataMap            " + mergedJobDataMap);
        System.out.println("|    refireCount                 " + refireCount);
        System.out.println("|    result                      " + result);
        System.out.println("|    scheduledFireTime           " + scheduledFireTime);
        System.out.println("|    scheduler                   " + scheduler);
        System.out.println("|    trigger                     " + trigger);
        // System.out.println("|    recoveringTriggerKey        " + recoveringTriggerKey);
        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|                             Job Detail                              |");
        System.out.println("|---------------------------------------------------------------------|");
        System.out.println("|    description                 " + jobDetail.getDescription());
        System.out.println("|    jobClass                    " + jobDetail.getJobClass());
        System.out.println("|    key                         " + jobDetail.getKey());
        //        System.out.println("|---------------------------------------------------------------------|");
        //        System.out.println("|                             Job DataMap                             |");
        //        System.out.println("|---------------------------------------------------------------------|");
        //        for (String key : mergedJobDataMap.getKeys()) {
        //            System.out.println("|    jobDataMap                  " + mergedJobDataMap.getString(key));
        //        }
        System.out.println("-----------------------------------------------------------------------");

        System.out.println("\n\n");
    }

}
