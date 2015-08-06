package org.moon.test.timer.events;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.embeddable.EJBContainer;
import javax.enterprise.event.Observes;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public class SchedulerTest {

    static Logger LOG = LoggerFactory.getLogger(SchedulerTest.class);
    EJBContainer container;
    @EJB
    Scheduler scheduler;

    @Before
    public void setUp() throws Exception {
        container = EJBContainer.createEJBContainer();
        container.getContext().bind("inject", this);
    }

    @Test
    public void test() throws Exception {
        assertNotNull(container);
        assertNotNull(scheduler);
        scheduler.scheduleEvent(new ScheduleExpression().hour("*").minute("*").second("*/5"), new TestEvent("Mary"));
        scheduler.scheduleEvent(new ScheduleExpression().hour("*").minute("*").second("*/10"), new TestEvent("David"));
        Thread.sleep(Long.MAX_VALUE);
    }

    public void observe1(@Observes TestEvent event) throws Exception {
        LOG.info("observer1 event message is " + event.getMessage());
    }

    public void observe2(@Observes TestEvent event) throws Exception {
        LOG.info("observer2 event message is " + event.getMessage());
    }

    @After
    public void tearDown() throws Exception {
        container.close();
    }

    class TestEvent {
        public String message;

        public TestEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
