package org.harmony.test.javaee.timer;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

public class TimerTest {

    public static void main(String[] args) throws InterruptedException, NamingException {
        EJBContainer container = EJBContainer.createEJBContainer();
        container.getContext().lookup("java:global/test-javaee-timer/SimpleTimerBean!org.harmony.test.javaee.timer.SimpleTimerBean");
        Thread.sleep(Long.MAX_VALUE);
    }
}
