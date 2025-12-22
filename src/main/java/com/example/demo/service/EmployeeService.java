package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    
    public Employee save(Employee employee) {

        if (employee.getMaxWeeklyHours() <= 0) {
            throw new RuntimeException("Max weekly hours must be greater than 0");
        }

        if (repo.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return repo.save(employee);
    }

   
    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

   
    public Employee update(Long id, Employee emp) {
        Employee existing = getById(id);

        existing.setFullName(emp.getFullName());
        existing.setSkills(emp.getSkills());
        existing.setRole(emp.getRole());
        existing.setMaxWeeklyHours(emp.getMaxWeeklyHours());

        return repo.save(existing);
    }

   
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
