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

import com.lms.model.Leave;
import com.lms.service.LeaveService;

import jakarta.validation.Valid;

/**
 * Leave Controller class for handling HTTP requests related to Leave requests
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
	private LeaveService leaveService;

	public LeaveController(LeaveService leaveService) {
		super();
		this.leaveService = leaveService;
	}
	@GetMapping
	public ResponseEntity<List<Leave>> getAll(){
		List<Leave> leaves = leaveService.getAllLeaves();
		return ResponseEntity.ok(leaves);
	}
	
	@GetMapping("/{leaveId}")
	public ResponseEntity<Leave> getLeaveById(@PathVariable int leaveId){
		Leave leave = leaveService.getByIdLeave(leaveId);
		return ResponseEntity.ok(leave);
	}
	
	@PostMapping
	public ResponseEntity<String> applyLeave(@Valid @RequestBody Leave leave){
		String result = leaveService.applyLeave(leave);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	@GetMapping("/employee/{empId}")
	public ResponseEntity<List<Leave>> getLeavesByEmployeeId(@PathVariable int empId){
		List<Leave> leaves = leaveService.findLeavsByEmployeeId(empId);
		if(!leaves.isEmpty()) {
			return ResponseEntity.ok(leaves);	
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{leaveId}")
	public ResponseEntity<String> updateLeave(@PathVariable int leaveId, @RequestBody Leave leave){
		String result = leaveService.updateLeave(leaveId, leave);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLeave(@PathVariable int id){
		String result = leaveService.deleteLeaveById(id);
		return ResponseEntity.ok(result);
	}
	
	 
	

}
