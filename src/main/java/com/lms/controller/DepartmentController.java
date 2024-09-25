package com.lms.controller;

import java.util.List;

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

import com.lms.model.Department;
import com.lms.service.DepartmentService;


import jakarta.validation.Valid;

/**
 * Department Controller class for handling HTTP requests related to Department 
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	private DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	@GetMapping
	public ResponseEntity<List<Department>> getAll(){
		List<Department> departments = departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	@GetMapping("/{deptCode}")
	public ResponseEntity<Department> getDepartmentByDeptCode(@PathVariable int deptCode){
		System.out.println(deptCode);
		Department department = departmentService.getByDeptCode(deptCode);
		return ResponseEntity.ok(department);
	}
	
	@PostMapping
	public ResponseEntity<String> addDepartment(@Valid @RequestBody Department department){
		String result = departmentService.addDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/{deptCode}")
	public ResponseEntity<String> updateDepartment(@PathVariable int deptCode, @RequestBody Department department){
		String result = departmentService.updateDepartment(deptCode, department);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{deptCode}")
	public ResponseEntity<String> deleteDepartment(@PathVariable int deptCode){
		String result = departmentService.deleteDepartment(deptCode);
		return ResponseEntity.ok(result);
	}

}
