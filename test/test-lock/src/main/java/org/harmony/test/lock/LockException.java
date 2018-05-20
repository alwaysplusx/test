package org.harmony.test.lock;

/**
 * @author wuxii@foxmail.com
 */
public class LockException extends RuntimeException {

    private static final long serialVersionUID = -3433702694984839255L;

    public LockException() {
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(Throwable cause) {
        super(cause);
    }

}
