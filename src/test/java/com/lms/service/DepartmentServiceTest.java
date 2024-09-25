package com.lms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lms.model.Department;
import com.lms.repository.DepartmentRepository;
import com.lms.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase
class DepartmentServiceTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DepartmentService departmentService;

	@Test
	void testGetAllDepartments() {
		Department department1 =  new Department();
		department1.setDeptcode(101);
		department1.setDeptName("MT");
		
		Department department2 =  new Department();
		department2.setDeptcode(102);
		department2.setDeptName("KT");
		
		Department department3 =  new Department();
		department3.setDeptcode(103);
		department3.setDeptName("LT");
		
		Department department4 =  new Department();
		department4.setDeptcode(104);
		department4.setDeptName("IT");
		
		departmentRepository.save(department1);
		departmentRepository.save(department2);
		departmentRepository.save(department3);
		departmentRepository.save(department4);
		
		List<Department> departments = departmentService.getAllDepartments();
		
		assertEquals(4, departments.size());
	}
//
//	@Test
//	void testGetByDeptCode() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testAddDepartment() {
//		Department department = Department.builder()
//				.deptcode(109)
//				.deptName("KT")
//				.wellnessLeave(10)
//				.casualLeave(10)
//				.priviledgeLeave(10)
//				.differnce(0)
//				.build();
//		
//		departmentRepository.save(department);
//		assertThat(department.getDeptcode()).isGreaterThan(0);
//		
//	}

//	@Test
//	void testUpdateDepartment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteDepartment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateAllEmployeeLeave() {
//		fail("Not yet implemented");
//	}

}
