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
    public ShiftTemplate create(@RequestBody ShiftTemplate template) {
        return service.create(template);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getByDepartment(@PathVariable Long departmentId) {
        return service.getByDepartment(departmentId);
    }
}
