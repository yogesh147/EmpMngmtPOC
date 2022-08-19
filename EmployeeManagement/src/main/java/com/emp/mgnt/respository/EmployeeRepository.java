package com.emp.mgnt.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.emp.mgnt.entity.EmployeeInfo;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Long> {

}
