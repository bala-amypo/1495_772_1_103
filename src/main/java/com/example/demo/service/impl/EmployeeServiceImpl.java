package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getMaxWeeklyHours() <= 0)
            throw new RuntimeException("max hours must be greater than 0");

        if (repository.existsByEmail(employee.getEmail()))
            throw new RuntimeException("Employee email exists");

        if (employee.getRole() == null)
            employee.setRole("STAFF");

        return repository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (updated.getEmail() != null &&
                !updated.getEmail().equals(existing.getEmail()) &&
                repository.existsByEmail(updated.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (updated.getFullName() != null) existing.setFullName(updated.getFullName());
        if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
        if (updated.getRole() != null) existing.setRole(updated.getRole());
        if (updated.getSkillsRaw() != null) existing.setSkills(updated.getSkillsRaw());

        return repository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        repository.delete(e);
    }

    @Override
    public Employee findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }
}
