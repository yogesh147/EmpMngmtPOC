package com.emp.mgnt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Department")
public class EmployeeDepartment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String name;
	
//	@OneToMany(mappedBy = "department")
//    private List<EmployeeInfo> employees;

	private String employeeName;

}
