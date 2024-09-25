package com.lms.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * This method represents an Department in the organization
 * Each department has a unique department code, department name, and allocated leave types. 
 * 
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="department_details")
public class Department {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int deptcode;
	@Column(nullable = false)
	private String deptName;
	@Column(nullable = false)
	private int wellnessLeave;
	@Column(nullable = false)
	private int casualLeave;
	@Column(nullable = false)
	private int priviledgeLeave;
	@Column(nullable = false)
	private int wellnessDifference;
	@Column(nullable = false)
	private int casualDifference;
	@Column(nullable = false)
	private int privilegeDifference;
	
	@JsonIgnore
	@OneToMany(mappedBy= "department",cascade = CascadeType.REMOVE)
	private List<Employee> employeeList;

}
