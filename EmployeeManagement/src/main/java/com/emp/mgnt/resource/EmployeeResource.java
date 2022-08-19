package com.emp.mgnt.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.mgnt.custom.exception.InvalidDepartmentException;
import com.emp.mgnt.entity.EmployeeInfo;
import com.emp.mgnt.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
/**
 * Controller file to handle client request type
 */

@RestController
@RequestMapping("/api/employee")
@SuppressWarnings({ "unchecked", "rawtypes" })
@Slf4j
public class EmployeeResource {

	@Autowired
	private EmployeeService empService;

	/**
	 * save Employee
	 * @param employee
	 * @return ResponseEntity of employee with http status
	 * @throws InvalidDepartmentException
	 */
	@PostMapping
	public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeInfo employee) throws InvalidDepartmentException {
		log.info("Inside saveEmployee :: {}", employee.getEmpDepartment());
		return new ResponseEntity(empService.saveEmployee(employee), HttpStatus.OK);
	}

	/**
	 * get all employees
	 * @return ResponseEntity of employee list with http status
	 */
	@GetMapping
	public ResponseEntity<?> getAllEmployees() {
		log.info("Inside getAllEmployees");
		return new ResponseEntity(empService.fetchEmployeeList(), HttpStatus.OK);
	}

	/**
	 * update Employee
	 * @param employee
	 * @param employeeId
	 * @return ResponseEntity of updated employee with http status
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeInfo employee, @PathVariable("id") Long employeeId) {
		log.info("Inside updateEmployee by id :: {}", employeeId);
		return new ResponseEntity(empService.updateEmployee(employee, employeeId), HttpStatus.OK);
	}

	/**
	 * @param employeeId
	 * @return ResponseEntity of deleted message with http status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long employeeId) {
		log.info("Inside updateEmployee by id :: {}", employeeId);
		empService.deleteEmployeeById(employeeId);
		return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
	}
}
