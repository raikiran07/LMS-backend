package com.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lms.exceptions.EmployeeNotFoundException;
import com.lms.exceptions.FieldNotNullException;
import com.lms.model.Employee;
import com.lms.repository.EmployeeRepository;
import com.lms.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{
	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private EmployeeRepository employeeRepository;

	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			EmployeeRepository employeeRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.employeeRepository = employeeRepository;
	}


	@Override
	public ResponseEntity<Map<String, String>>  login(Employee employee) {
		
		Map<String, String> map = new HashMap<>();
		if(employee.getUsername()==null || employee.getEmpPassword()==null) {
			throw new FieldNotNullException("Field can't be empty");
		}
		
		Employee emp = employeeRepository.findByUsername(employee.getUsername()).orElseThrow(()-> new EmployeeNotFoundException("No employee present with username "+ employee.getUsername()));
		
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getEmpPassword());
		
		try {
			Authentication authentication = authenticationManager.authenticate(authToken);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String token = jwtTokenProvider.generateToken(authentication);

			String empId = String.valueOf(emp.getEmpId());
			map.put("token", token);
			map.put("username", emp.getUsername());
			map.put("role", emp.getRole());
			map.put("empId", empId);
			
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}
		catch(AuthenticationException e) {
			map.put(e.getMessage(), "Password is incorrect");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
		
	}

}
