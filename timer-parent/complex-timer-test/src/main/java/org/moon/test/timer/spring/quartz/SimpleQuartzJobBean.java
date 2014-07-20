package org.moon.test.timer.spring.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SimpleQuartzJobBean extends QuartzJobBean {

	static Logger LOG = LoggerFactory.getLogger(SimpleQuartzJobBean.class);
	@Autowired
	private ApplicationContext context;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOG.info("has execute, context is " + context);
	}

}
