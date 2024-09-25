package com.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Employee;
import com.lms.service.EmployeeService;
import jakarta.validation.Valid;

/**
 * Employee Controller class for handling HTTP requests related to Employee
 *
 */

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	@GetMapping
	public ResponseEntity<List<Employee>> getAll(){
		List<Employee> employees= employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable int id){
		Employee employee = employeeService.getById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping
	public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee){
		String result = employeeService.addEmployee(employee);
		return ResponseEntity.ok(result);
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
		String result = employeeService.updateEmployee(id, employee);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id){
		String result = employeeService.deleteEmployee(id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/department/{deptCode}")
	public ResponseEntity<List<Employee>> getEmployeesBydepartment(@PathVariable int deptCode){
		List<Employee> employees=  employeeService.getEmployeesByDepartmentCode(deptCode);
		return ResponseEntity.ok(employees);
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<Employee> getEmployeeByUsername(@RequestBody Employee emp){
		
		Optional<Employee> currentEmp = employeeService.getEmployeeByUsername(emp.getUsername());
		
		if(currentEmp.isEmpty()) {
			String msg = "username not available";
			return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
		}
		Employee currentEmployee = currentEmp.get();
		
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
		
		
		
		
		
	}
	
}
