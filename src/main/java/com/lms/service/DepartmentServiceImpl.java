package com.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.exceptions.DepartmentNotFoundException;
import com.lms.exceptions.DepartmentWithDeptCodeAlreadyExists;
import com.lms.exceptions.FieldNotNullException;
import com.lms.model.Department;

import com.lms.repository.DepartmentRepository;

/**
 * Implementation of the DepartmentService interface
 * Handles CRUD operations and business logic related to departments
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> getAllDepartments() {
		// Retrieve all departments from the repository
		return departmentRepository.findAll();
	}

	@Override
	public Department getByDeptCode(int deptcode) {
		// Retrieve a department by its ID from the repository 
		return departmentRepository.findById(deptcode).orElseThrow(()-> new DepartmentNotFoundException
				("Department with deptcode "+ deptcode+" not found"));
	}

	@Override
	public String addDepartment(Department department) {
		// Check if any field is null before saving the department
		if(department.getDeptName()==null || department.getCasualLeave()==0 || department.getPriviledgeLeave()==0
				|| department.getWellnessLeave()==0 ) {
			throw new FieldNotNullException("Field can't be empty");
		}
		Optional<Department> deptOpt= departmentRepository.findById(department.getDeptcode());
		if(deptOpt.isPresent()) {
			throw new DepartmentWithDeptCodeAlreadyExists("Department with deptcode "+ department.getDeptcode()+" already exists");
		}
		Optional<Department> deptOpt1= departmentRepository.findByDeptName(department.getDeptName());
		if(!deptOpt1.isEmpty()) {
			throw new DepartmentWithDeptCodeAlreadyExists("Department with deptName "+ department.getDeptName()+" already exists");
		}
		// save the department to the repository
		departmentRepository.save(department);
		 return "Department with name "+department.getDeptName()+" saved successfully";
		}

	@Override
	public String updateDepartment(int deptcode, Department department) {
		//Check if the department code is present
		Optional<Department> deptOpt= departmentRepository.findById(deptcode);
		if(deptOpt.isEmpty()) {
			throw new DepartmentNotFoundException("Department with deptcode "+ deptcode+" not found");
		}
		Department dept = deptOpt.get();
		
		
		if(department.getDeptName()!=null) {
//			int diff= 0;
			dept.setDeptName(department.getDeptName());
			
		}
		
		if(department.getCasualLeave()!=0) {
			int diff= 0;
			diff = department.getCasualLeave()-dept.getCasualLeave();
			dept.setCasualLeave(department.getCasualLeave());
			dept.setCasualDifference(diff);
		}
		
		if(department.getPriviledgeLeave()!=0) {
			int diff= 0;
			diff= department.getPriviledgeLeave()-dept.getPriviledgeLeave();
			dept.setPriviledgeLeave(department.getPriviledgeLeave());
			dept.setPrivilegeDifference(diff);
		}
		
		if(department.getWellnessLeave()!=0) {
			int diff= 0;
			diff = department.getWellnessLeave()-dept.getWellnessLeave();
			dept.setWellnessLeave(department.getWellnessLeave());
			dept.setWellnessDifference(diff);
		}
			
		departmentRepository.save(dept);
		
		return "Department updated Successfully";
	}

	@Override
	public String deleteDepartment(int deptcode) {
		// Check if the department code is present
		Optional<Department> deptOpt= departmentRepository.findById(deptcode);
		if(deptOpt.isEmpty()) {
			throw new DepartmentNotFoundException("Department with deptcode "+ deptcode+" not found");
		}
		// delete the department with the specified ID from the repository 
		departmentRepository.deleteById(deptcode);
		return "department deleted Successfully";
	}

	@Override
	public String updateAllEmployeeLeave(int deptcode) {
		return "updated all employee leave";
	}
	
	

	

}
