package com.example.demo.repository;

import com.example.demo.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Department save(Department department);

    Optional<Department> findById(Long id);

    List<Department> findAll();

    boolean existsByName(String name);

    void delete(Department department);
}
