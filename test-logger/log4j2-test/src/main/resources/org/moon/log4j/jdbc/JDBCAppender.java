package org.moon.log4j.jdbc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.spi.LoggingEvent;

public class JDBCAppender extends org.apache.log4j.jdbc.JDBCAppender {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);
    private boolean locationInfo = false;

    @Override
    public void close() {
        super.close();
        threadPool.shutdownNow();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void append(LoggingEvent event) {
        event.getNDC();
        event.getThreadName();
        event.getMDCCopy();
        if (locationInfo) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        buffer.add(event);
        threadPool.execute(new FlushTask());
    }

    class FlushTask implements Runnable {

        @Override
        public void run() {
            flushBuffer();
        }

    }

}
