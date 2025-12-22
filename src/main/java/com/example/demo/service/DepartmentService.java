package com.example.employee.service;

import com.example.employee.entity.Department;
import com.example.employee.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    
    public Department create(Department department) {

        if (repo.existsByName(department.getName())) {
            throw new RuntimeException("Department name already exists");
        }

        if (department.getRequiredSkills() == null ||
            department.getRequiredSkills().isEmpty()) {
            throw new RuntimeException("Required skills must be defined");
        }

        return repo.save(department);
    }

   
    public List<Department> getAll() {
        return repo.findAll();
    }

    
    public Department getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    
    public Department update(Long id, Department dept) {

        Department existing = getById(id);

        existing.setDescription(dept.getDescription());
        existing.setRequiredSkills(dept.getRequiredSkills());

        return repo.save(existing);
    }

   
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
