package com.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.exceptions.EmployeeNotFoundException;
import com.lms.exceptions.FieldNotNullException;
import com.lms.exceptions.LeaveNotFoundException;
import com.lms.model.Employee;
import com.lms.model.Leave;
import com.lms.repository.EmployeeRepository;
import com.lms.repository.LeaveRepository;

/**
 * Implementation of the LeaveService interface
 * Handles CRUD operations and business logic related to leave requests
 *
 */

@Service
public class LeaveServiceImpl implements LeaveService{
	private LeaveRepository leaveRepository;
	private EmployeeRepository employeeRepository;

	
	public LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
		super();
		this.leaveRepository = leaveRepository;
		this.employeeRepository=employeeRepository;
	}

	@Override
	public List<Leave> getAllLeaves() {
		//Retrieve all leave requests from the repository
		return leaveRepository.findAll();
	}

	@Override
	public Leave getByIdLeave(int leaveId) {	
		// Retrieve a leave request by its Id from the repository
		return leaveRepository.findById(leaveId).orElseThrow(()-> new LeaveNotFoundException			
				("Leave with id "+ leaveId+"not found"));
	}

	@Override
	public String applyLeave(Leave leave) {
		// Check if any field is null before adding the leave request
		if(leave.getStartDate()==null || leave.getLeaveType()== null || leave.getEndDate()==null || 
				leave.getTransactionType()==null || leave.getReason()==null || leave.getSessionType()==null) {
			throw new FieldNotNullException("Field can't be empty");
		}	
		if(leave!=null && leave.getEmployee()!=null) {
			leave.setStatus("Pending");
			leave.setComments(" ");
			// save the leave request to the repository
			leaveRepository.save(leave);
			return "Leave with empid "+leave.getEmployee().getEmpId() +" saved successfully";
		}
		else return "Leave not updated";
	}

	@Override
	public String updateLeave(int leaveId, Leave leaveDetails) {
		// Check if the leave request ID is present
		Optional<Leave> leaveOpt= leaveRepository.findById(leaveId);
		if(leaveOpt.isEmpty()) {
			throw new EmployeeNotFoundException("Leave with id "+ leaveId+" not found");
		}
		Leave leav = leaveOpt.get();	
		int noOfDays = 2;
		leav.setStatus(leaveDetails.getStatus());
		leav.setComments(leaveDetails.getComments());
		leaveRepository.save(leav);
//		if(leav.getStatus().equals("Approved")) {
//			Employee employee = leav.getEmployee();
//			if(leav.getLeaveType().equals("Wellness Leave")){
//				employee.setWellnessLeave(employee.getWellnessLeave()-noOfDays);
//			}
//			else if(leav.getLeaveType().equals(" Casual Leave")) {
//				employee.setCasualLeave(employee.getCasualLeave()-noOfDays);
//			}
//			else if (leav.getLeaveType().equals(" Privilege Leave")) {
//				employee.setPrivilegeLeave(employee.getPrivilegeLeave()-noOfDays);
//			}
//			employeeRepository.save(employee);
//		}
		return "Leave with id" + leaveId+ " updated Successfully";
	}

	@Override
	public List<Leave> findLeavsByEmployeeId(int empId){
		return leaveRepository.findByEmployeeId(empId);
	}

	@Override
	public String deleteLeaveById(int id) {
		// Check if the leave request ID is present
		Optional<Leave> empOpt= leaveRepository.findById(id);
		if(empOpt.isEmpty()) {
			throw new LeaveNotFoundException("Leave with id "+ id+" not found");
		}
		// delete the leave request with the specified ID from the repository
		leaveRepository.deleteById(id);
		return "Leave deleted Successfully";
	}

}
