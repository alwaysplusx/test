package org.moon.test.timer.meta;

import org.moon.test.timer.meta.api.Organic;
import org.moon.test.timer.meta.api.Secondly;
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
