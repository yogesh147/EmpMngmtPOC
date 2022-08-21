package com.emp.mgnt.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.mgnt.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
