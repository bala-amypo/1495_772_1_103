package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
            throw new IllegalArgumentException("Employee with email " + employee.getEmail() + " already exists");
        }

        if (employee.getMaxWeeklyHours() == null || employee.getMaxWeeklyHours() <= 0) {
            throw new IllegalArgumentException("Max weekly hours must be greater than 0");
        }

        // Set default role if not provided
        if (employee.getRole() == null) {
            employee.setRole("STAFF");
        }

        employee.setCreatedAt(LocalDateTime.now());

        // Convert skills Set<String> to comma-separated String if needed
        // Assuming service receives Set<String> via some DTO
        // Example: Set<String> skillsSet = ...;
        // employee.setSkills(String.join(",", skillsSet));

        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployee(id);

        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        existing.setRole(employee.getRole() != null ? employee.getRole() : existing.getRole());
        existing.setMaxWeeklyHours(employee.getMaxWeeklyHours());
        existing.setSkills(employee.getSkills());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existing = getEmployee(id);
        employeeRepository.delete(existing);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
