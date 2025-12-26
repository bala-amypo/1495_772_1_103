package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department create(Department department) {
        if (repository.existsByName(department.getName()))
            throw new RuntimeException("Department exists");

        return repository.save(department);
    }

    @Override
    public Department get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public void delete(Long id) {
        Department d = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        repository.delete(d);
    }

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }
}
