package com.lms.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.exceptions.EmployeeNotFoundException;
import com.lms.model.Employee;
import com.lms.repository.EmployeeRepository;

import java.util.Arrays;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private EmployeeRepository employeeRepository;
	
	public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByUsername(username).orElseThrow(()->new EmployeeNotFoundException("No employee with this username"));
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(employee.getRole()));
		
		return new User(employee.getUsername(), employee.getEmpPassword(), authorities);
	}


	

}
