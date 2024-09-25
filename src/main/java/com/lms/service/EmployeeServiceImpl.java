package com.lms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lms.exceptions.EmployeeNotFoundException;
import com.lms.exceptions.FieldNotNullException;
import com.lms.model.Employee;
import com.lms.repository.DepartmentRepository;
import com.lms.repository.EmployeeRepository;

/**
 * Implementation of the EmployeeService interface
 * Handles CRUD operations and business logic related to employees
 *
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Employee> getAllEmployees() {
		//Retrieve all employees from the repository
		return employeeRepository.findAll();
	}

	@Override
	public Employee getById(int id) {
		//Retrieve an employee by their Id from the repository
		return employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException
				("Employee with id "+ id+"not found"));
	}
	@Override
	public String addEmployee(Employee employee) {
		// Check if any field is null before saving the employee
		if(employee.getEmpId()==0 || employee.getEmpName()==null || employee.getEmpPassword()==null || employee.getEmpPosition()==null
			||	employee.getHr()==null || employee.getManager()==null || employee.getUsername()==null || 
			employee.getPrivilegeLeave()==0 || employee.getCasualLeave()==0 || employee.getWellnessLeave()==0 || employee.getRole()==null) {
			throw new FieldNotNullException("Field can't be empty");
		}
			
		 employee.setEmpPassword(passwordEncoder.encode(employee.getEmpPassword()));
		// save the employee to the repository 
		employeeRepository.save(employee);
		 return "Employee with name "+employee.getEmpName()+" saved successfully";
	}

	@Override
	public String updateEmployee(int id, Employee employee) {
		// Check if the employee ID is present
		Optional<Employee> empOpt= employeeRepository.findById(id);
		if(empOpt.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with id "+ id+" not found");
		}
		Employee emp = empOpt.get();
		if(employee.getEmpName()!=null) {
			emp.setEmpName(employee.getEmpName());
		}
		if(employee.getCasualLeave()!=0) {
			emp.setCasualLeave(employee.getCasualLeave());	
		}
		if(employee.getPrivilegeLeave()!=0) {
			emp.setPrivilegeLeave(employee.getPrivilegeLeave());
		}
		if(employee.getWellnessLeave()!=0) {
			emp.setWellnessLeave(employee.getWellnessLeave());
		}
		if(employee.getEmpPassword()!=null) {
			 emp.setEmpPassword(passwordEncoder.encode(employee.getEmpPassword()));
		}
		if(employee.getEmpPosition()!=null) {
			emp.setEmpPosition(employee.getEmpPosition());
		}
		if(employee.getHr()!=null) {
			emp.setHr(employee.getHr());
		}
		if(employee.getManager()!=null) {
			emp.setManager(employee.getManager());
		}
		if(employee.getRole()!=null) {
			emp.setRole(employee.getRole());
		}
		if(employee.getUsername()!=null) {
			emp.setUsername(employee.getUsername());
		}

//		if(employee.getDepartment().getDeptcode()!=0) {
//			emp.getDepartment().setDeptcode(employee.getDepartment().getDeptcode());
//		}
		//implement other properties too
		employeeRepository.save(emp);
		return "Employee updated Successfully";
		
	}

	@Override
	public String deleteEmployee(int id) {
		// Check if the employee ID is present
		Optional<Employee> empOpt= employeeRepository.findById(id);
		if(empOpt.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with id "+ id+" not found");
		}
		// Delete the employee with the specified ID from the repository
			employeeRepository.deleteById(id);
		return "Employee deleted Successfully";
	}

	@Override
	public List<Employee> getEmployeesByDepartmentCode(int deptCode) {
		return employeeRepository.findByDepartmentDeptCode(deptCode);
	}

	@Override
	public Optional<Employee> getEmployeeByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Employee employee =employeeRepository.findByUsername(username)
//				.orElseThrow(()->new UsernameNotFoundException("Not found"));
//		
//		List<GrantedAuthority> authorities=
//				Arrays.asList(new SimpleGrantedAuthority(employee.getRole()));
//		
//		return new org.springframework.security.core.userdetails.User(employee.getUsername()
//				,employee.getEmpPassword(),authorities);
//		
//	}

//	@Override
//	public Employee findByUserNameAndPassword(String username, String password) {
//		// TODO Auto-generated method stub
//		Employee employee = employeeRepository.findByUserNameAndPassword(username, password);
//		if(employee==null) {
//			throw new RuntimeException()
//		}
//		return null;
//	}


//	@Override
//	public List<Leave> getALlLeavesByEmpId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
}