package org.harmony.test.javaee.jpa.query;

public class PersistenceUnitNameNotFindException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PersistenceUnitNameNotFindException() {
        super();
    }

    public PersistenceUnitNameNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceUnitNameNotFindException(String message) {
        super(message);
    }

    public PersistenceUnitNameNotFindException(Throwable cause) {
        super(cause);
    }

}
