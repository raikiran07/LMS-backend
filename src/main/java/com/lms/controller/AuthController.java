package com.lms.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.exceptions.EmployeeNotFoundException;
import com.lms.model.Employee;
import com.lms.repository.EmployeeRepository;
import com.lms.service.AuthService;

/**
 * Auth Controller class for handling HTTP requests related to Login requests
 *
 */
@CrossOrigin
@RestController
public class AuthController {  
    @Autowired
    private AuthService authService;
	@Autowired
	private EmployeeRepository employeeRepository;
    
    @PostMapping("/api/login")
    
    //added
    public ResponseEntity<Map<String, String>>  login (@RequestBody Employee employee){
    	return authService.login(employee);
    }
 
//    public ResponseEntity<Employee> login(@RequestBody Employee employee){   
//    	
//    	String username = employee.getUsername();
//    	String password = employee.getEmpPassword();
// 
//    	System.out.println("USERNAME "+ username+"\n"+" PASSWORD "+password );
//    	System.out.println(employee);
//    	Employee responseEmployee = null;
//    	
//        try {
//        	responseEmployee =employeeRepository.findByUsername(username).orElseThrow(()-> new EmployeeNotFoundException
//    				("Employee with username  "+ username+"not found"));
//          Authentication user = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//          System.out.println("\nAUTHENTICATION"+user+"\n");
//        }
//        catch (AuthenticationException e){ 
//            return new ResponseEntity("Password is incorrect",HttpStatus.UNAUTHORIZED);
//        } 
//        return ResponseEntity.ok(responseEmployee);
//        
//    }
    
}