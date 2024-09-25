package com.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.lms.exceptions.DepartmentNotFoundException;
import com.lms.exceptions.DepartmentWithDeptCodeAlreadyExists;
import com.lms.exceptions.EmployeeException;
import com.lms.exceptions.EmployeeNotFoundException;
import com.lms.exceptions.FieldNotNullException;
import com.lms.exceptions.LeaveNotFoundException;

/**
 * Global Exception handler to handle all exceptions in the application.
 */
//@RestControllerAdvice
@ControllerAdvice
public class ErrorHandler {
	
	/**
	 *  Handles EmployeeNotFoundException.
	 * @param The WebRequest object
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 *  Handles DepartmentNotFoundException.
	 * @param The WebRequest object
	 * @return ResponseEntity containing the error response
	 */
	
	@ExceptionHandler({DepartmentNotFoundException.class})
	public ResponseEntity<String> handleDepartmentNotFound(DepartmentNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 *  Handles LeaveNotFoundException.
	 * @param ex
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler({LeaveNotFoundException.class})
	public ResponseEntity<String> handleLeaveNotFound(LeaveNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 *  Handles DepartmentWithDeptCodeAlreadyExists.
	 * @param ex The WebRequest object
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler({DepartmentWithDeptCodeAlreadyExists.class})
	public ResponseEntity<String> handleDepartmentAlreadyExists(DepartmentWithDeptCodeAlreadyExists ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handles FieldNotNullException
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FieldNotNullException.class)
    public ResponseEntity<String> handleFieldCantBeNullExceptions(FieldNotNullException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	/**
	 * Handles Employee Exception for sending HTTP status and error message
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({EmployeeException.class})
	public ResponseEntity<String> handleEmployeeNotFound(EmployeeException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles MethodArgumentNotValidException
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> handleInvalidData(MethodArgumentNotValidException ex){
		List<FieldError> errors = ex.getFieldErrors();
		StringBuffer sb = new StringBuffer();
		for(FieldError fieldError : errors) {
			sb.append(fieldError.getDefaultMessage()+"\n");
		}
		return new ResponseEntity<String>(sb.toString(),HttpStatus.BAD_REQUEST);
	}

}
