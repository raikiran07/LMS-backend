package com.lms.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.model.Department;
import com.lms.model.Employee;
import com.lms.model.Leave;
import com.lms.repository.DepartmentRepository;
import com.lms.repository.EmployeeRepository;
import com.lms.repository.LeaveRepository;
import com.lms.service.DepartmentService;


@SpringBootTest
class DepartmentServiceTest {
	
	@Autowired
	private DepartmentRepository dRepo;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LeaveRepository lRepo;

	//test to get all departments' details
	@Test
	@Order(2)
	void testGetAllDepartments() {
		List<Department> list=dRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	//test to get a department details based on the department code
	@Test
	void testGetByDeptCode() {
		assertNotNull(dRepo.findById(200));
	}

	//test to add a new department
	@Test
	@Order(1)
	void testAddDepartment() {
	
		Department dept =  new Department();
		dept.setDeptcode(107);
		dept.setDeptName("Sales");
		dept.setWellnessLeave(3);
		dept.setPriviledgeLeave(5);
		dept.setCasualLeave(8);
		
		dRepo.save(dept);
		assertNotNull(dRepo.findById(107));
	}

	//test to update the details of a particular department
	@Test
	@Order(3)
	void testUpdateDepartment() {
		Department dept=dRepo.findById(107).get();
		dept.setDeptName("IT");
		dRepo.save(dept);
     	assertEquals("IT",dRepo.findById(107).get().getDeptName());
	}

	//test to delete a particular department
	@Test
	@Order(4)
	void testDeleteDepartment() {
		dRepo.deleteById(107);
	    assertThat(dRepo.existsById(107)).isFalse();
	}


}
