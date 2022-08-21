package com.emp.mgnt.resource;

import java.util.List;

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
import com.emp.mgnt.entity.EmployeeResponseDTO;
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
	 * 
	 * @param employee
	 * @return ResponseEntity of employee with http status
	 * @throws InvalidDepartmentException
	 */
	@PostMapping
	public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeInfo employee) throws InvalidDepartmentException {
		log.info("Inside saveEmployee :: {}", employee.getName());
		return new ResponseEntity(empService.saveEmployee(employee), HttpStatus.OK);
	}

	/**
	 * get all employees
	 * 
	 * @return ResponseEntity of employee list with http status
	 */
	@GetMapping
	public ResponseEntity<?> getAllEmployees() {
		log.info("Inside getAllEmployees");
		return new ResponseEntity(empService.fetchEmployeeList(), HttpStatus.OK);
	}

	/**
	 * update Employee
	 * 
	 * @param employee
	 * @param employeeId
	 * @return ResponseEntity of updated employee with http status
	 */
	@PutMapping
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeInfo employee) {
		log.info("Inside updateEmployee by id :: {}", employee.getId());
		return new ResponseEntity(empService.updateEmployee(employee), HttpStatus.OK);
	}

	/**
	 * @param employeeId
	 * @return ResponseEntity of deleted message with http status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long employeeId) {
		log.info("Inside updateEmployee by id :: {}", employeeId);
		empService.deleteEmployeeById(employeeId);
		return new ResponseEntity("Employee Deleted Successfully", HttpStatus.OK);
	}

	/**
	 * get employee By Department Name
	 * 
	 * @return ResponseEntity of employee list with http status
	 */
	@GetMapping("/{departmentName}")
	public ResponseEntity<?> getEmployeeByDepartmentName(@PathVariable("departmentName") String name) {
		log.info("Inside getEmployeeByDepartmentName");
		return new ResponseEntity(empService.fetchEmployeeListByDepartmentName(name), HttpStatus.OK);
	}

	/**
	 * get Join Information employee By Department Name
	 * 
	 * @return ResponseEntity of employee list with http status
	 */
	@GetMapping("/getInfo")
	public List<EmployeeResponseDTO> getJoinInformation() {
		return empService.getJoinInformation();
	}
	
	/**
	 * delete Task by Id
	 * 
	 * @return ResponseEntity of employee list with http status
	 */
	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
		empService.deleteTask(id);
		return new ResponseEntity("Task Deleted Successfully", HttpStatus.OK);
	}
}
