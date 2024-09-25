package com.lms.service;

import java.util.List;

import com.lms.model.Leave;

/**
 * Service interface for managing Leave entities
 * Defines methods for CRUD operations and other business logic related to leave history
 *
 */

public interface LeaveService {
	
	/**
	 * Retrieves all leave requests.
	 * @return A list of all leave requests
	 */
	List<Leave> getAllLeaves();
	
	/**
	 * Retrieves a leave requests by its ID.
	 * @param id
	 * @return the leave request with the specified ID, or null if not found
	 */
	Leave getByIdLeave(int id);
	String applyLeave(Leave leave);
	String updateLeave(int leaveId, Leave leave);
	String deleteLeaveById(int id);
	List<Leave> findLeavsByEmployeeId(int empId);
	

}
