package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public DepartmentEntity save(@RequestBody DepartmentEntity dept) {
        return service.save(dept);
    }

    @GetMapping
    public List<DepartmentEntity> getAll() {
        return service.findAll();
    }
}
