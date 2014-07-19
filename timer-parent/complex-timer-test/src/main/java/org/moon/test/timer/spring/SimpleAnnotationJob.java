package org.moon.test.timer.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SimpleAnnotationJob {

	static Logger LOG = LoggerFactory.getLogger(SimpleAnnotationJob.class);

	@Scheduled(fixedDelay = 10000)
	public void handle() {
		LOG.info("handle execute every 1 second");
	}

}
