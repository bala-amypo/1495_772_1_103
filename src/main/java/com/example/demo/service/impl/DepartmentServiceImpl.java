package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Department create(Department department) {
        if (repo.existsByName(department.getName())) {
            throw new IllegalArgumentException("Department exists");
        }
        return repo.save(department);
    }

    @Override
    public Department get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public void delete(Long id) {
        Department d = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        repo.delete(d);
    }

    @Override
    public List<Department> getAll() {
        return repo.findAll();
    }
}
