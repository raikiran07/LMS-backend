package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.lms.model.Leave;

/**
 * Repository interface for Leave entities
 * Provides CRUD operations for managing Leave entities in the database
 *
 */

@EnableJpaRepositories
public interface LeaveRepository extends JpaRepository<Leave, Integer>{

	@Query(value="select * from leave_details where empId = :empId",nativeQuery = true)
	List<Leave> findByEmployeeId(@Param("empId") int empId);

	
}
