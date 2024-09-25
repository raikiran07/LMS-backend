package com.lms.exceptions;

/**
 * Exception thrown when a leave request is not found in the database
 *
 */
public class LeaveNotFoundException extends RuntimeException {

	public LeaveNotFoundException() {
		super();
		}

	public LeaveNotFoundException(String message) {
		super(message);
		}

	
}
