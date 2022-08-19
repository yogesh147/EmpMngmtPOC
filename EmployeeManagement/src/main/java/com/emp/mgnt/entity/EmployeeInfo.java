package com.emp.mgnt.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EmployeeInfo")
@Builder
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class EmployeeInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long empId;
	
	@NotBlank
/*	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "emp_roles",
			joinColumns = @JoinColumn(name = "emp_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)*/
//	private  Set<EmployeeRole> empRole = new HashSet<>();
	private String empRole;

	@NotBlank
	private String empUser;

//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
//	private Set<EmployeeDepartment> empDepartment = new HashSet<>();
	
	private String empDepartment;

	@NotBlank
	private String empInfo;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private final ZonedDateTime createdAt = ZonedDateTime.now();

}
