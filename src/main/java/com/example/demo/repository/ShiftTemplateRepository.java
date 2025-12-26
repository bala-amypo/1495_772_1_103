package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplate, Long> {

    ShiftTemplate findByTemplateNameAndDepartment_Id(String templateName, Long departmentId);

    List<ShiftTemplate> findByDepartment_Id(Long departmentId);
}
