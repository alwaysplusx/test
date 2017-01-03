package org.moon.test.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPrinter {

    static final Logger log = LoggerFactory.getLogger(LoggerPrinter.class);

    public static void main(String[] args) {
        log.info("abc");
    }

}
