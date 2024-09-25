package com.lms.exceptions;

/**
 * Exception thrown when a department is not found in the database
 *
 */
public class DepartmentNotFoundException extends RuntimeException {

	public DepartmentNotFoundException() {
		super();
	}

	public DepartmentNotFoundException(String message) {
		super(message);
	}

}
