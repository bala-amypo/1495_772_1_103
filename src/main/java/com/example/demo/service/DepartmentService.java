package com.example.employee.service;

import com.example.employee.entity.DepartmentEntity;
import com.example.employee.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public DepartmentEntity create(DepartmentEntity department) {

        if (repo.existsByName(department.getName())) {
            throw new RuntimeException("Department name already exists");
        }

        if (department.getRequiredSkills() == null ||
                department.getRequiredSkills().isEmpty()) {
            throw new RuntimeException("Required skills must be defined");
        }

        return repo.save(department);
    }

    public List<DepartmentEntity> getAll() {
        return repo.findAll();
    }

    public DepartmentEntity getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public DepartmentEntity update(Long id, DepartmentEntity dept) {

        DepartmentEntity existing = getById(id);

        existing.setDescription(dept.getDescription());
        existing.setRequiredSkills(dept.getRequiredSkills());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
