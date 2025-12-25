package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();

    Optional<Employee> findByEmail(String email);

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    void deleteEmployee(Long id);
}
