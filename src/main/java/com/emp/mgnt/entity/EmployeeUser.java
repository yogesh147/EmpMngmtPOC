package com.emp.mgnt.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class EmployeeUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id; 
}
