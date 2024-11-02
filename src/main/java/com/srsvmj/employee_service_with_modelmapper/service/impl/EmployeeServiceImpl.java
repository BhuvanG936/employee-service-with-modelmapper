package com.srsvmj.employee_service_with_modelmapper.service.impl;


import com.srsvmj.employee_service_with_modelmapper.dto.EmployeeDTO;
import com.srsvmj.employee_service_with_modelmapper.entity.Employee;
import com.srsvmj.employee_service_with_modelmapper.exception.ResourceNotFoundException;
import com.srsvmj.employee_service_with_modelmapper.repository.EmployeeRepository;
import com.srsvmj.employee_service_with_modelmapper.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Employee savedEmployee =  employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee Not Exist with the given id: "+id));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) {

        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee does not exist with given id: "+id));

        employee.setId(updatedEmployee.getId());
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj =  employeeRepository.save(employee);

        return modelMapper.map(updatedEmployeeObj, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee Not Exist with the given id: "+id));

        employeeRepository.delete(employee);
    }
}