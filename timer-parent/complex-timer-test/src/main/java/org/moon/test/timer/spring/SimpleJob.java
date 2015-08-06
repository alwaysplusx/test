package org.moon.test.timer.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SimpleJob {

    static Logger LOG = LoggerFactory.getLogger(SimpleJob.class);

    @Autowired
    private SimpleService simpleService;
    
    @Scheduled(fixedDelay = 10000)
    public void handle() {
        LOG.info("spring annotation schedule task execute every 10 second, simple service is " + simpleService);
    }

    public SimpleService getSimpleService() {
        return simpleService;
    }

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

}
