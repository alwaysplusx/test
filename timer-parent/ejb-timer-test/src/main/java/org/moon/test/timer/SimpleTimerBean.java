package org.moon.test.timer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class SimpleTimerBean {

    private static Logger LOG = LoggerFactory.getLogger(SimpleTimerBean.class);
    @Resource
    TimerService timerService;
    
    @PostConstruct
    public void postConstruct() {
        LOG.debug("postConstruct");
        TimerConfig timerConfig = new TimerConfig("default_task", false);
        ScheduleExpression schedule = new ScheduleExpression().year("*").month("*").dayOfMonth("*").hour("*").minute("*/1");
        timerService.createCalendarTimer(schedule, timerConfig);
    }

    @Timeout
    public void timeout(Timer timer) {
        Serializable task = timer.getInfo();
        if ("default_task".equals(task)) {
            LOG.info("default task execute");
        }
    }

    @PreDestroy
    public void perDestroy() {
        LOG.debug("preDestroy");
    }

}
