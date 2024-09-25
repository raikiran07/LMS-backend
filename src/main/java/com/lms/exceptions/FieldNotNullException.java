package com.lms.exceptions;

/**
 * Exception thrown when any field is null
 *
 */
public class FieldNotNullException extends RuntimeException {

	public FieldNotNullException() {
		super();
		}

	public FieldNotNullException(String message) {
		super(message);
		}

}
