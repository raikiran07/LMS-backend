package com.lms.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.model.Department;
import com.lms.model.Employee;
import com.lms.model.Leave;
import com.lms.repository.EmployeeRepository;
import com.lms.repository.LeaveRepository;
import com.lms.service.EmployeeService;
@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class LeaveServiceTest {
	
	@Autowired
	LeaveRepository lRepo;
	@Autowired
	EmployeeRepository eRepo;
	@Autowired
	EmployeeService es;

	//Test to get the data of all the leaves
	@Test
	@Order(2)
	void testGetAllLeaves() {
		List<Leave> list=lRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	//test to get leaves based on the provided id
	@Test
	@Order(3)
	void testGetByIdLeave() {
		assertNotNull(lRepo.findById(80));
	}

	//test to apply a new leave
	@Test
	@Order(1)
	void testApplyLeave() {
		Leave leave=new Leave();
		leave.setLeaveId(80);
		leave.setTransactionType("Half Day");
		leave.setLeaveType("Casual Leave");
		String str = "2024-02-27";
		String str1 = "2024-02-28";
		Date date = Date.valueOf(str);
		Date date1 = Date.valueOf(str1);
		leave.setStartDate(date);
		leave.setEndDate(date1);
		leave.setComments("Preksha's leave");
		leave.setStatus("Pending");
		leave.setReason("Wedding");
		lRepo.save(leave);
		assertNotNull(lRepo.findById(80));
	}

	//testing the updation of leaves
	@Test
	@Order(4)
	void testUpdateLeave() {
		Leave leave= lRepo.findById(202).get();
		leave.setLeaveType("Wellness Leave");;
		lRepo.save(leave);
     	assertEquals("Wellness Leave",lRepo.findById(202).get().getLeaveType());
	}

	//test to get the leaves based on id
	@Test
	@Order(6)
	void testFindLeavsByEmployeeId() {
		List<Leave> list=lRepo.findByEmployeeId(200);
		assertNotNull(list);
	}

	//test to delete a leave based on id.
	@Test
	@Order(5)
	void testDeleteLeaveById() {
		lRepo.deleteById(202);
	    assertThat(lRepo.existsById(202)).isFalse();
	}

}
