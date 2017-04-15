package org.moon.test.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogPrinter {

    private Logger log = LogManager.getLogger();

    public void print() {
        log.info("simple log info message! result is {}", "success");
    }

    public static void main(String[] args) {
        new LogPrinter().print();
    }

}
