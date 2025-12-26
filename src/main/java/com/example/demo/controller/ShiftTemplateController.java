package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift-templates")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateController(ShiftTemplateService shiftTemplateService,
                                   DepartmentRepository departmentRepository) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<ShiftTemplate>> byDepartment(
            @PathVariable Long deptId) {
        return ResponseEntity.ok(shiftTemplateService.getByDepartment(deptId));
    }

    @PostMapping
    public ResponseEntity<ShiftTemplate> create(
            @RequestBody ShiftTemplate shiftTemplate) {
        return ResponseEntity.ok(shiftTemplateService.create(shiftTemplate));
    }
}
