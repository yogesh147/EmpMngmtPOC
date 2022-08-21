package com.emp.mgnt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emp.mgnt.custom.exception.InvalidDepartmentException;
import com.emp.mgnt.entity.EmployeeInfo;
import com.emp.mgnt.entity.EmployeeResponseDTO;
import com.emp.mgnt.entity.Task;
import com.emp.mgnt.respository.EmployeeRepository;
import com.emp.mgnt.respository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private TaskRepository taskRepository;

	/**
	 * save Employee
	 * 
	 * @param employee
	 * @return Employee object
	 * @throws InvalidDepartmentException
	 */
	public EmployeeInfo saveEmployee(EmployeeInfo employee) throws InvalidDepartmentException {
		log.info("Inside saveEmployee service layer");

		/*
		 * Optional<Employee> emp = Optional.ofNullable(employee); if(emp.isPresent()) {
		 * } emp.ifPresent(e -> System.out.println(e.getEmpDepartment()));
		 */
		if (!StringUtils.hasText(employee.getName())) {
			throw new InvalidDepartmentException("Employee name not found");
		}
		return empRepository.save(employee);
	}

	/**
	 * fetch Employee List
	 * 
	 * @return employees
	 */
	public List<EmployeeInfo> fetchEmployeeList() {
		return (List<EmployeeInfo>) empRepository.findAll();
	}

	/**
	 * update Employee
	 * 
	 * @param employee
	 * @param empId
	 * @return updated Employee object
	 */
	public EmployeeInfo updateEmployee(EmployeeInfo employee) {
		EmployeeInfo employeeDb = empRepository.findById(employee.getId()).get();

		if (Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName())) {
			employeeDb.setName(employee.getName());
		}

		if (Objects.nonNull(employee.getDepartmentName()) && !"".equalsIgnoreCase(employee.getDepartmentName())) {
			employeeDb.setDepartmentName(employee.getDepartmentName());
		}

		if (Objects.nonNull(employee.getTasks()) && !employee.getTasks().isEmpty() && employee.getTasks().size() > 0) {
			List<Task> dbTask = employeeDb.getTasks();
			employeeDb.setTasks(new ArrayList<>());

			employee.getTasks().forEach(t -> {
				Task task = new Task();
				Optional<Task> record = dbTask.stream().filter(f -> f.getId().equals(t.getId())).findAny();
				if (record.isPresent()) {
					Task tsk = record.get();
					task.setId(tsk.getId());
				}
				task.setTaskName(t.getTaskName());
				employeeDb.getTasks().add(task);
			});
		}
		return empRepository.save(employeeDb);
	}

	/**
	 * delete Employee By Id
	 * 
	 * @param EmployeeId
	 */
	public void deleteEmployeeById(Long EmployeeId) {
		empRepository.deleteById(EmployeeId);
	}

	/**
	 * fetch Employee List By DepartmentName
	 * 
	 * @param name
	 * @return employees
	 */
	public List<EmployeeInfo> fetchEmployeeListByDepartmentName(String name) {
		return empRepository.findByDepartmentName(name);
	}

	public List<EmployeeResponseDTO> getJoinInformation() {
		return empRepository.getJoinInformation();
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
}
