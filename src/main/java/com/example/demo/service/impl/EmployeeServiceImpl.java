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
            throw new IllegalArgumentException("Employee email already exists");
        }

        if (employee.getMaxWeeklyHours() == null || employee.getMaxWeeklyHours() <= 0) {
            throw new IllegalArgumentException("Max weekly hours must be > 0");
        }

        if (employee.getRole() == null) {
            employee.setRole("STAFF");
        }

        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee e = getEmployee(id);
        e.setFullName(employee.getFullName());
        e.setEmail(employee.getEmail());
        e.setSkills(String.join(",", employee.getSkills())); // store as comma-separated
        e.setMaxWeeklyHours(employee.getMaxWeeklyHours());
        e.setRole(employee.getRole());
        return employeeRepository.save(e);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee e = getEmployee(id);
        employeeRepository.delete(e);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
