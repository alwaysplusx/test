package org.moon.test.timer.quartz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTimerTest {

	Class<? extends Job> defaultJob = SimpleJob.class;
	
	Scheduler scheduler;
	JobDetail jobDetail;
	Trigger trigger;

	@Before
	public void setUp() throws Exception {
		scheduler = StdSchedulerFactory.getDefaultScheduler();
		jobDetail = JobBuilder.newJob(defaultJob).withIdentity("SimpleJobDetail").build();
		trigger = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5)).build();
	}

	@Test
	public void testQuartzTimer() throws Exception {
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();

		Thread.sleep(1000 * 3);
	}

	@After
	public void tearDown() throws Exception {
		scheduler.shutdown();
	}

}
