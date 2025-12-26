package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployee(Long id);

    Employee updateEmployee(Long id, Employee updatedEmployee);

    void deleteEmployee(Long id);

    Employee findByEmail(String email);

    List<Employee> getAll();
}
