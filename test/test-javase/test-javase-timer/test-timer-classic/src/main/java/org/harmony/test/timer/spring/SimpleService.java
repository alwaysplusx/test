package org.harmony.test.timer.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    static Logger log = LoggerFactory.getLogger(SimpleService.class);

    public void doBusiness() {
        log.info("simple do business logic");
    }

}
