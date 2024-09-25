package com.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import com.lms.model.Employee;

/**
 * Service interface for managing Employee entities
 * Defines methods for CRUD operations and other business logic related to employees
 *
 */
public interface EmployeeService {

	/**
	 * Retrieves all employees
	 * @return A list of all employees
	 */
	List<Employee> getAllEmployees();
	
	/**
	 * Retrieves an employee by their ID.
	 * @param id
	 * @return the emplopyee with the specified ID, or null if not found
	 */
	Employee getById(int id);
	String addEmployee(Employee employee);
	String updateEmployee(int id, Employee employee);
	String deleteEmployee(int id);
	List<Employee> getEmployeesByDepartmentCode(@Param("deptCode") int deptCode);
	Optional<Employee> getEmployeeByUsername(String username);
	
//	void findByUserNameAndPassword(String username, String password);
//	List<Leave> getALlLeavesByEmpId();
	
}
