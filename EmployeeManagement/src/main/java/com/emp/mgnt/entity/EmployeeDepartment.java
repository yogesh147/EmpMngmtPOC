package com.emp.mgnt.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity 	
@Data
@Table(name = "EmployeeDepartment")
public class EmployeeDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String depId;
	
	private String depName;
	
	private String depRegion;
	
	private String depPincode;
	
	@OneToMany(mappedBy = "department")
    private List<EmployeeInfo> employees;

}
