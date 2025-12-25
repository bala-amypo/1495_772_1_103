package com.example.demo.controller;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift-templates")
public class ShiftTemplateController {

    private final ShiftTemplateService service;

    public ShiftTemplateController(ShiftTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public ShiftTemplate create(@RequestBody ShiftTemplate st) {
        return service.create(st);
    }

    @GetMapping("/department/{id}")
    public List<ShiftTemplate> getByDepartment(@PathVariable Long id) {
        return service.getByDepartment(id);
    }

    @GetMapping
    public List<ShiftTemplate> getAll() {
        return service.getAll();
    }
}
