package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentEntity save(DepartmentEntity dept) {
        return repository.save(dept);
    }

    public List<DepartmentEntity> findAll() {
        return repository.findAll();
    }
}
