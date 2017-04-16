package org.harmony.test.timer.spring.quartz;

import org.harmony.test.timer.spring.SimpleService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class SimpleQuartzJobBean extends QuartzJobBean {

    static Logger LOG = LoggerFactory.getLogger(SimpleQuartzJobBean.class);
    @Autowired
    private SimpleService simpleService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOG.info("has execute, service is " + simpleService);
    }

    public SimpleService getSimpleService() {
        return simpleService;
    }

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

}
