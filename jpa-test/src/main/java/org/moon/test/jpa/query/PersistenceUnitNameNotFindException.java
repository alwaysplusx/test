package org.moon.test.jpa.query;

public class PersistenceUnitNameNotFindException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PersistenceUnitNameNotFindException() {
		super();
	}

	public PersistenceUnitNameNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersistenceUnitNameNotFindException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceUnitNameNotFindException(String message) {
		super("persistence unit name not find");
	}

	public PersistenceUnitNameNotFindException(Throwable cause) {
		super(cause);
	}

}
