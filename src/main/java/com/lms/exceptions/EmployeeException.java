package com.lms.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Employee Exception handles employee exception and sending HTTP request and error messages
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeException extends RuntimeException {
	private HttpStatus status;
	private String message;

}
