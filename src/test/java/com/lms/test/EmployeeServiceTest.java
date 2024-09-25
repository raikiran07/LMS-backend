package com.lms.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.model.Department;
import com.lms.model.Employee;
import com.lms.repository.DepartmentRepository;
import com.lms.repository.EmployeeRepository;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {
	
	@Autowired
	EmployeeRepository eRepo;
	@Autowired
	DepartmentRepository dRepo;

	//test to get all employees data
	@Test
	@Order(2)
	void testGetAllEmployees() {
		List<Employee> list=eRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	//test to get employee details based on id
	@Test
	@Order(3)
	void testGetById() {
		assertNotNull(eRepo.findById(10));
	}

	//test to add a new employee
	@Test
	@Order(1)
	void testAddEmployee() {
		Employee employee = new Employee();
		employee.setEmpId(10);
		employee.setEmpName("Preksha");
		employee.setEmpPassword("123456");
		employee.setEmpPosition("user");
		employee.setManager("raju");
		employee.setHr("admin");
		employee.setCasualLeave(10);
		employee.setPrivilegeLeave(5);
		employee.setWellnessLeave(3);
//		employee.getDepartment().setDeptcode(102);
		employee.setUsername("aditya");
		employee.setRole("Employee");
		eRepo.save(employee);
		assertNotNull(eRepo.findById(10));
	}

	//test to update an employee's details
	@Test
	@Order(4)
	void testUpdateEmployee() {
		Employee emp=eRepo.findById(10).get();
		emp.setManager("manu");
		eRepo.save(emp);
     	assertEquals("manu",eRepo.findById(10).get().getManager());
	}

	//test to delete an employee
	@Test
	@Order(5)
	void testDeleteEmployee() {
		eRepo.deleteById(10);
	    assertThat(eRepo.existsById(10)).isFalse();
	}

	//test to get list of employees details based on the department code
	@Test
	void testGetEmployeesByDepartmentCode() {
		List<Employee>list= eRepo.findByDepartmentDeptCode(200);
		System.out.println(list);
		assertNotNull(list);
	}

}

