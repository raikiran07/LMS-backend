package com.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.model.Department;

/**
 * Repository interface for Department entities
 * Provides CRUD operations for managing Department entities in the database
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
//	@Query(value = "select * from department where deptName=?")
	Optional<Department> findByDeptName(String deptName);
}
