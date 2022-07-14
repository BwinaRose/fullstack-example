package com.bwina.springboot.repos;

import com.bwina.springboot.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
