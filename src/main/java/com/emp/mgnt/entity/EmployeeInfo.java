package com.emp.mgnt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee_info")
public class EmployeeInfo {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	public int id;

}
