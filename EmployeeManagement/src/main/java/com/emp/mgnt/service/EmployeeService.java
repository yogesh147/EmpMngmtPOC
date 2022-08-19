package com.emp.mgnt.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emp.mgnt.custom.exception.InvalidDepartmentException;
import com.emp.mgnt.entity.EmployeeInfo;
import com.emp.mgnt.respository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;

	/**
	 * save Employee
	 * @param employee
	 * @return Employee object
	 * @throws InvalidDepartmentException
	 */
	public EmployeeInfo saveEmployee(EmployeeInfo employee) throws InvalidDepartmentException {
		log.info("Inside saveEmployee service layer");
		
	/*	Optional<Employee> emp = Optional.ofNullable(employee);
		if(emp.isPresent()) {
		}
		emp.ifPresent(e -> System.out.println(e.getEmpDepartment()));
		*/
		if (!StringUtils.hasText(employee.getEmpUser())) {
			throw new InvalidDepartmentException("Employee department not found");
		}
		return empRepository.save(employee);
	}
	
	/**
	 * fetch Employee List
	 * @return employees
	 */
	public List<EmployeeInfo> fetchEmployeeList() {
		return (List<EmployeeInfo>) empRepository.findAll();
	}

	/**
	 * update Employee
	 * @param employee
	 * @param empId
	 * @return updated Employee object
	 */
	public EmployeeInfo updateEmployee(EmployeeInfo employee, Long empId) {
		EmployeeInfo employeeDb = empRepository.findById(empId).get();

		if (Objects.nonNull(employee.getEmpRole())) {
			employeeDb.setEmpRole(employee.getEmpRole());
		}

		if (Objects.nonNull(employee.getEmpUser()) && !"".equalsIgnoreCase(employee.getEmpUser())) {
			employeeDb.setEmpUser(employee.getEmpUser());
		}

		if (Objects.nonNull(employee.getEmpDepartment()) && !"".equalsIgnoreCase(employee.getEmpUser())) {
			employeeDb.setEmpDepartment(employee.getEmpDepartment());
		}

		if (Objects.nonNull(employee.getEmpInfo()) && !"".equalsIgnoreCase(employee.getEmpInfo())) {
			employeeDb.setEmpInfo(employee.getEmpInfo());
		}
		return empRepository.save(employeeDb);
	}

	/**
	 * delete Employee By Id
	 * @param EmployeeId
	 */
	public void deleteEmployeeById(Long EmployeeId) {
		empRepository.deleteById(EmployeeId);
	}
}
