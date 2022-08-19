package com.emp.mgnt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity 	
@Data
public class EmployeeRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id; 
	
	private String name; 
	
	private String description; 
}

