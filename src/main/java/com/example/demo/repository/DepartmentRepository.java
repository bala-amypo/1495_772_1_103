package com.example.employee.repository;

import com.example.employee.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<DepartmentEntity, Long> {

    boolean existsByName(String name);
}
