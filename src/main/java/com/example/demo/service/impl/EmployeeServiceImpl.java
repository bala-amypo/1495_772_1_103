package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee createEmployee(Employee e) {
        if (repo.existsByEmail(e.getEmail()))
            throw new RuntimeException("Employee already exists");

        if (e.getMaxHoursPerWeek() <= 0)
            throw new RuntimeException("Hours must be greater than zero");

        if (e.getRole() == null)
            e.setRole("STAFF");

        return repo.save(e);
    }

    public Employee getEmployee(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, Employee e) {
        Employee old = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!old.getEmail().equals(e.getEmail()) && repo.existsByEmail(e.getEmail()))
            throw new RuntimeException("Email exists");

        old.setFullName(e.getFullName());
        old.setEmail(e.getEmail());
        old.setRole(e.getRole());
        old.setSkills(e.getSkills());
        old.setMaxHoursPerWeek(e.getMaxHoursPerWeek());

        return repo.save(old);
    }

    public void deleteEmployee(Long id) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        repo.delete(e);
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
