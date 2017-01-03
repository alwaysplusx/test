package org.moon.test.ioc.core;

public class BeanLoadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BeanLoadException() {
        super();
    }

    public BeanLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BeanLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanLoadException(String message) {
        super(message);
    }

    public BeanLoadException(Throwable cause) {
        super(cause);
    }
    
}
