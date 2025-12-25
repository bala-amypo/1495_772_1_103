package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // Get all employees
    List<Employee> getAll();

    // Find an employee by email
    Optional<Employee> findByEmail(String email);

    // Save or update an employee
    Employee save(Employee employee);

    // Delete an employee by ID
    void deleteEmployee(Long id);
}
