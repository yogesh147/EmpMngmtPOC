package com.emp.mgnt.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emp.mgnt.entity.EmployeeInfo;
import com.emp.mgnt.entity.EmployeeResponseDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Long> {
	
	List<EmployeeInfo> findByDepartmentName(String DepartmentName);
	
	@Query("SELECT new com.emp.mgnt.entity.EmployeeResponseDTO(c.name , p.taskName) FROM EmployeeInfo c JOIN c.tasks p")
    public List<EmployeeResponseDTO> getJoinInformation();

}
