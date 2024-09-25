package com.lms.exceptions;

/**
 * Exception thrown when department code already exists in the database
 *
 */
public class DepartmentWithDeptCodeAlreadyExists extends RuntimeException{

	public DepartmentWithDeptCodeAlreadyExists() {
		super();
		}

	public DepartmentWithDeptCodeAlreadyExists(String message) {
		super(message);
		}
}
