package com.example.employee.repository;

import com.example.employee.entity.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {

    boolean existsByTemplateNameAndDepartmentId(
            String templateName,
            Long departmentId
    );
}
