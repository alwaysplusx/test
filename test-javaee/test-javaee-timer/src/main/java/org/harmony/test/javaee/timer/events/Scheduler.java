package org.harmony.test.javaee.timer.events;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.inject.spi.BeanManager;

@Singleton
public class Scheduler {

    @Resource
    private TimerService timerService;
    @Resource
    private BeanManager beanManager;

    public void scheduleEvent(ScheduleExpression schedule, Object event, Annotation... qualifiers) {
        timerService.createCalendarTimer(schedule, new TimerConfig(new Event(event, qualifiers), false));
    }

    @Timeout
    public void timeout(Timer timer) {
        Event task = (Event) timer.getInfo();
        beanManager.fireEvent(task.getEvent(), task.getQualifiers());
    }

    private final class Event implements Serializable {

        private static final long serialVersionUID = 1L;
        private final Object event;
        private final Annotation[] qualifiers;

        public Event(Object event, Annotation... qualifiers) {
            this.event = event;
            this.qualifiers = qualifiers;
        }

        public Object getEvent() {
            return event;
        }

        public Annotation[] getQualifiers() {
            return qualifiers;
        }
    }

}
