package com.lms.service;

import java.util.List;
import com.lms.model.Department;

/**
 * Service interface for managing Department entities
 * Defines methods for CRUD operations and other business logic related to departments
 *
 */

public interface DepartmentService {
	
	/**
	 * Retrieves all departments
	 * @return A list of all departments
	 */
	List<Department> getAllDepartments();
	
	/**
	 * Retrieves a department by its department code
	 * @param deptcode
	 * @return the department with the specified department code, or null if not found
	 */
	Department getByDeptCode(int deptcode);
	
	/**
	 * Add department in the database
	 * @param department
	 * @return 
	 */
	String addDepartment(Department department);
	String updateDepartment(int deptcode, Department department);
	String deleteDepartment(int deptcode);
	String updateAllEmployeeLeave(int deptcode);

}
