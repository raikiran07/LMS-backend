package com.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * This method represents an Employee in the organization
 * Each employee has a unique identifiers, name, role, username, password, assigned manager,
 * HR representative, leave balances position and belong to a department
 * 
 */
@NoArgsConstructor
@Data
@Entity
@Table(name="employee__details")
public class Employee {
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(nullable = false)
	private int empId;
	@Pattern(regexp = "[A-za-z\s]{3,}", message = "Employee name should contain only alphabet")
	@Column(nullable = false)
	private String empName;
	@Column(nullable = false)
	private String empPassword;
	@Column(nullable = false)
	private String empPosition;
	@Column(nullable = false)
	private String manager;
	@Column(nullable = false)
	private String hr;
	@Column(nullable = false)
	private int wellnessLeave;
	@Column(nullable = false)
	private int casualLeave;
	@Column(nullable = false)
	private int privilegeLeave;
	@JsonIgnore
	@OneToMany(mappedBy= "employee",cascade = CascadeType.REMOVE)
	private List<Leave> leave;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptCode", referencedColumnName = "deptCode")
	private Department department;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String role;

	public Employee(int empId,
			@Pattern(regexp = "[A-za-z ]{3,}", message = "Employee name should contain only alphabet") String empName,
			String empPassword, String empPosition, String manager, String hr, int wellnessLeave, int casualLeave,
			int privilegeLeave, Department department, List<Leave> leave) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empPosition = empPosition;
		this.manager = manager;
		this.hr = hr;
		this.leave=leave;
		this.wellnessLeave = this.department.getWellnessLeave();
		this.casualLeave = this.department.getCasualLeave();
		this.privilegeLeave = this.department.getPriviledgeLeave();
		this.department = department;
	}
	

}

