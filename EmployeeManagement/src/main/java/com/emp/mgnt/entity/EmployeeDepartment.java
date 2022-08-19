package com.emp.mgnt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity 	
@Data
@Table(name = "EmployeeDepartment")
public class EmployeeDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String depId;
	
//	@ManyToOne
	private String depName;
	
	private String depRegion;
	
	private String depPincode;

}
