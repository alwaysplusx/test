package org.harmony.test.javaee.timer;

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

    private static final Logger log = LoggerFactory.getLogger(SimpleTimerBean.class);
    @Resource
    TimerService timerService;

    @PostConstruct
    public void postConstruct() {
        log.debug("postConstruct");
        timerService.createCalendarTimer(
                new ScheduleExpression()//
                        .year("*")//
                        .month("*")//
                        .dayOfMonth("*")//
                        .hour("*")//
                        .minute("*/1"), //
                new TimerConfig("default_task", false));
    }

    @Timeout
    public void timeout(Timer timer) {
        Serializable task = timer.getInfo();
        if ("default_task".equals(task)) {
            log.info("default task execute every one minute");
        }
    }

    @PreDestroy
    public void perDestroy() {
        log.debug("preDestroy");
    }

}
