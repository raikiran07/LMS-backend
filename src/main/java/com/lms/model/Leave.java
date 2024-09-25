package com.lms.model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * This method represents an Leave in the organization
 * Each Leave History entry has a unique identifiers, startdate, end date, leave type, reason, status,
 * comments, session type and is associated with an Employee
 * 
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "leave_details")
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empId", referencedColumnName = "empId")
	private Employee employee;
	@Column(nullable = false)
	private String transactionType;
	@Column(nullable = false)
	private Date startDate;
	@Column(nullable = false)
	private Date endDate;
	@Pattern(regexp = "(Wellness Leave|Casual Leave|Privilege Leave)", message = "Invalid Leave Type")
	@Column(nullable = false)
	private String leaveType;
	@Column(nullable = false)
	private String reason;
	@Pattern(regexp = "(Pending|Approved|Rejected)", message = "Invalid Status")
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private String comments;
	
	private int noOfDays;
//	@Column(nullable = false)
	private Date appliedOnDate;
	@Pattern(regexp = "(First Half|Second Half)", message = "Invalid Session Type")
	private String sessionType;

	
//	@OneToMany(mappedBy = "employee")
//	private List<Leave> leaves;

}
