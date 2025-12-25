package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee e);

    Employee getEmployee(Long id);

    Employee updateEmployee(Long id, Employee e);

    void deleteEmployee(Long id);

    List<Employee> getAll();

    Employee findByEmail(String email);
}
