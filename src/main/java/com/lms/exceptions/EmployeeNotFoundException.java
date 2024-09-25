package com.lms.exceptions;

/**
 * Exception thrown when an employee is not found in the database
 *
 */
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super();
		}

	public EmployeeNotFoundException(String message) {
		super(message);
		}

	
}
