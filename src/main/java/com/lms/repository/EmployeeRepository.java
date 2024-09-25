package com.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.model.Employee;

/**
 * Repository interface for Employee entities
 * Provides CRUD operations for managing Employee entities in the database
 *
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value="select * from employee__details where deptCode = :deptCode",nativeQuery = true)
	List<Employee> findByDepartmentDeptCode(int deptCode);
	
	@Query(value="select * from employee__details where deptCode = :deptCode",nativeQuery = true)
	List<Employee> findEmployeesByDepartmentCode(@Param("deptCode") int deptCode);
	
	Optional<Employee> findByUsername(String username);
}
