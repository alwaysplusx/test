package org.harmony.test.javaee.timer.meta;

import org.harmony.test.javaee.timer.meta.api.Organic;
import org.harmony.test.javaee.timer.meta.api.Secondly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Organic
public class TimerBean {

    static Logger LOG = LoggerFactory.getLogger(TimerBean.class);

    @Secondly
    public void handle() {
        LOG.info("timer bean handle every second");
    }

}
