package com.lms.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.lms.model.Employee;

/**
 * Auth interface for managing Login requests
  */
public interface AuthService {

	ResponseEntity<Map<String, String>> login(Employee employee);
	
	
}
