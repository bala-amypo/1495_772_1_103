package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (repo.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Employee email exists");
        }
        if (employee.getMaxWeeklyHours() == null || employee.getMaxWeeklyHours() <= 0) {
            throw new IllegalArgumentException("max hours must be greater");
        }
        if (employee.getRole() == null) {
            employee.setRole("STAFF");
        }
        return repo.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (updated.getEmail() != null &&
                !updated.getEmail().equals(existing.getEmail()) &&
                repo.existsByEmail(updated.getEmail())) {
            throw new IllegalArgumentException("Employee email exists");
        }

        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setRole(updated.getRole());
        existing.setSkills(updated.getSkillsRaw());
        existing.setMaxWeeklyHours(updated.getMaxWeeklyHours());

        return repo.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        repo.delete(e);
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
