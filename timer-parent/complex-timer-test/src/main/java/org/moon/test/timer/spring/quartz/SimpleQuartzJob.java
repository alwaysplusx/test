package org.moon.test.timer.spring.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleQuartzJob implements Job {

	static Logger LOG = LoggerFactory.getLogger(SimpleQuartzJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("handle execute");
	}

}
