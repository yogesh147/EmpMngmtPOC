package com.emp.mgnt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_info")
public class EmployeeInfo {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long id;

}
