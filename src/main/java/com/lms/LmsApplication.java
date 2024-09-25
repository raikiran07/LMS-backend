package com.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Leave Management System Application
 *  
 *  This application facilitates the management of employee leave requests and tracking of absence within an organization
 *  
 *  Key Features:
 *  Employees can submit leave requests.
 *  Managers can review, approve, or reject leave requests.
 *  Leave balances are automatically updated upon approval
 *  Provides reports and analytics on leave usage
 *  
 *  By automating the leave management process, this system ensures efficient handling of leave requests and promotes 
 *  transparency between employees and management
 *  
 * @author sharmar7
 *
 */

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
