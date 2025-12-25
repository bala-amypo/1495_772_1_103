package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Department create(Department d) {
        if (repo.existsByName(d.getName())) {
            throw new RuntimeException("Department already exists");
        }
        return repo.save(d);
    }

    @Override
    public Department get(Long id) {   // ✅ EXACT MATCH
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        repo.deleteById(id);
    }

    @Override
    public List<Department> getAll() {
        return repo.findAll();
    }
}
