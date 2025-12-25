package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new IllegalArgumentException("Department with name already exists");
        }
        department.setCreatedAt(LocalDateTime.now());
        return departmentRepository.save(department);
    }

    @Override
    public Department get(Long id) {
        if (id != null) {
            return departmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Department not found"));
        }
        throw new IllegalArgumentException("ID cannot be null");
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Department department = departmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            if (department != null) {
                departmentRepository.delete(department);
            }
        }
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}