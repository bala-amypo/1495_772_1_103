package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DepartmentEntity;

public interface DepartmentRepository
        extends JpaRepository<DepartmentEntity, Long> {
}
