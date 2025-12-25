package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
