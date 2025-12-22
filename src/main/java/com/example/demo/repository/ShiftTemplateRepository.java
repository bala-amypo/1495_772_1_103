package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ShiftTemplateEntity;

public interface ShiftTemplateRepository
        extends JpaRepository<ShiftTemplateEntity, Long> {

    Optional<ShiftTemplateEntity> findByTemplateNameAndDepartment_Id(
            String templateName, Long departmentId);
}
