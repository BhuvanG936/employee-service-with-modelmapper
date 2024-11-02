package com.srsvmj.employee_service_with_modelmapper.service;


import com.srsvmj.employee_service_with_modelmapper.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    com.srsvmj.employee_service_with_modelmapper.dto.EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
