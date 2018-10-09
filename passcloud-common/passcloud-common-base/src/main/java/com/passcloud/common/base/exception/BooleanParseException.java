package com.passcloud.common.base.exception;

/**
 * The class Boolean parse exception.
 *
 * @author liyuzhang
 */
public class BooleanParseException extends RuntimeException {

	/**
	 * Instantiates a new Boolean parse exception.
	 */
	public BooleanParseException() {
		super();
	}

	/**
	 * Instantiates a new Boolean parse exception.
	 *
	 * @param message the message
	 */
	public BooleanParseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Boolean parse exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public BooleanParseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Boolean parse exception.
	 *
	 * @param cause the cause
	 */
	public BooleanParseException(Throwable cause) {
		super(cause);
	}

}
