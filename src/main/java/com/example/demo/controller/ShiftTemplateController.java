package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateController(ShiftTemplateService shiftTemplateService,
                                   DepartmentRepository departmentRepository) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }

    public ResponseEntity<List<ShiftTemplate>> byDepartment(Long deptId) {
        return ResponseEntity.ok(shiftTemplateService.getByDepartment(deptId));
    }

    public ResponseEntity<ShiftTemplate> create(ShiftTemplate shiftTemplate) {
        return ResponseEntity.ok(shiftTemplateService.create(shiftTemplate));
    }
}
