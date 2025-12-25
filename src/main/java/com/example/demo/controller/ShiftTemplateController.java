package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    private final ShiftTemplateService service;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateController(ShiftTemplateService service,
                                   DepartmentRepository departmentRepository) {
        this.service = service;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping("/department/{departmentId}")
    public ResponseEntity<ShiftTemplate> create(
            @PathVariable Long departmentId,
            @RequestBody ShiftTemplate template) {

        template.setDepartment(
                departmentRepository.findById(departmentId).orElse(null)
        );
        return ResponseEntity.ok(service.create(template));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ShiftTemplate>> byDepartment(
            @PathVariable Long departmentId) {
        return ResponseEntity.ok(service.getByDepartment(departmentId));
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(service.getByDepartment(null));
    }
}
