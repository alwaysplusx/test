package org.harmony.test.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author wuxii@foxmail.com
 */
public class BizService {

    private static final Logger log4jLogger = LogManager.getLogger(BizService.class);

    private static final org.apache.logging.log4j.Logger log4j2Logger = org.apache.logging.log4j.LogManager.getLogger(BizService.class);

    public static void main(String[] args) {
        log4jLogger.info("Hello Log4j");
        log4j2Logger.info("Hello Log4j2");
    }
}
