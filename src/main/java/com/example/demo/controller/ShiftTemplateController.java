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

    private final ShiftTemplateService service;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateController(ShiftTemplateService service,
                                   DepartmentRepository departmentRepository) {
        this.service = service;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<ShiftTemplate> create(@RequestBody ShiftTemplate st) {
        return ResponseEntity.ok(service.create(st));
    }
}
