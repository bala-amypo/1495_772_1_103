package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (repo.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("exists");
        }
        if (employee.getMaxWeeklyHours() <= 0) {
            throw new IllegalArgumentException("must");
        }
        if (employee.getRole() == null) {
            employee.setRole("STAFF");
        }
        return repo.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (updated.getEmail() != null &&
            !updated.getEmail().equals(existing.getEmail()) &&
            repo.existsByEmail(updated.getEmail())) {
            throw new IllegalArgumentException("exists");
        }

        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setRole(updated.getRole());
        existing.setMaxWeeklyHours(updated.getMaxWeeklyHours());
        // ⚠️ DO NOT TOUCH skills (getter returns Set, field is String)

        return repo.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        repo.delete(e);
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Employee findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
