package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Employee with email already exists");
        }
        if (employee.getMaxWeeklyHours() != null && employee.getMaxWeeklyHours() <= 0) {
            throw new IllegalArgumentException("Max weekly hours must be greater than 0");
        }
        if (employee.getRole() == null) {
            employee.setRole("STAFF");
        }
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        if (id != null) {
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        }
        throw new IllegalArgumentException("ID cannot be null");
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        if (id != null) {
            Employee existing = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            existing.setFullName(employee.getFullName());
            existing.setEmail(employee.getEmail());
            existing.setRole(employee.getRole());
            existing.setSkills(employee.getSkills());
            existing.setMaxWeeklyHours(employee.getMaxWeeklyHours());
            return employeeRepository.save(existing);
        }
        throw new IllegalArgumentException("ID cannot be null");
    }

    @Override
    public void deleteEmployee(Long id) {
        if (id != null) {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            if (employee != null) {
                employeeRepository.delete(employee);
            }
        }
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}