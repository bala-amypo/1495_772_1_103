package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> emp = employeeRepository.findByEmail(email);
        return emp.orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getMaxWeeklyHours() == 0) {
            employee.setMaxWeeklyHours(40); // default max hours
        }
        employee.setCreatedAt(java.time.LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
