package com.example.demo.repository;

import com.example.demo.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    Optional<Employee> findByEmail(String email);

    List<Employee> findAll();

    boolean existsByEmail(String email);

    void delete(Employee employee);
}
