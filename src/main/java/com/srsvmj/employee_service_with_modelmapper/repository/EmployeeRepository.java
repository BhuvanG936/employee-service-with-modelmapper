package com.srsvmj.employee_service_with_modelmapper.repository;

import com.srsvmj.employee_service_with_modelmapper.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
