package org.moon.test.timer.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job {

	private static Logger log = LoggerFactory.getLogger(SimpleJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("execute simple job");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Calendar calendar = context.getCalendar();
		String instanceId = context.getFireInstanceId();
		Date fireTime = context.getFireTime();
		JobDetail jobDetail = context.getJobDetail();
		Job jobInstance = context.getJobInstance();
		long jobRunTime = context.getJobRunTime();
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		//Date nextFireTime = context.getNextFireTime();
		//Date previousFireTime = context.getPreviousFireTime();
		//TriggerKey recoveringTriggerKey = context.getRecoveringTriggerKey();
		int refireCount = context.getRefireCount();
		Object result = context.getResult();
		Date scheduledFireTime = context.getScheduledFireTime();
		Scheduler scheduler = context.getScheduler();
		Trigger trigger = context.getTrigger();
		
		StringBuffer sb = new StringBuffer();
		try {
			sb
			.append("***********************************************************************").append("\n")
			.append("*                JobExecutionContext Information                      *").append("\n")
			.append("***********************************************************************").append("\n")
			.append("* 	instanceId          :").append(instanceId).append("*").append("\n")
			.append("* 	fireTime            :").append(sdf.format(fireTime)).append("*").append("\n")
			.append("* 	jobDetail           :").append(jobDetail.getKey()).append("*").append("\n")
			.append("* 	jobInstance         :").append(jobInstance.toString()).append("*").append("\n")
			.append("* 	jobRunTime          :").append(jobRunTime).append("*").append("\n")
			.append("* 	mergedJobDataMap    :").append(mergedJobDataMap).append("*").append("\n")
			.append("* 	refireCount         :").append(refireCount).append("*").append("\n")
			.append("* 	result              :").append(result).append("*").append("\n")
			.append("* 	scheduledFireTime	:").append(scheduledFireTime).append("*").append("\n")
			.append("* 	scheduler	:").append(scheduler.getSchedulerName()).append("*").append("\n")
			.append("* 	trigger	: ").append(trigger.getKey()).append("*").append("\n")
			.append("***********************************************************************");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println(sb.toString());
	}

}
