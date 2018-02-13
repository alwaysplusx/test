package org.harmony.test.timer.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleTask {

    static Logger LOG = LoggerFactory.getLogger(SimpleTask.class);
    
    @Autowired
    private SimpleService simpleService;

    public void doWork() {
        LOG.info("execute work, simple service is " + simpleService);
    }

    public SimpleService getSimpleService() {
        return simpleService;
    }

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

}
