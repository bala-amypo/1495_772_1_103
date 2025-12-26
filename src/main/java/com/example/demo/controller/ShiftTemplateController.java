package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    private final ShiftTemplateService service;
    private final DepartmentService departmentService;

    public ShiftTemplateController(ShiftTemplateService service,
                                   DepartmentService departmentService) {
        this.service = service;
        this.departmentService = departmentService;
    }

    @PostMapping("/department/{departmentId}")
    public ShiftTemplate create(@PathVariable Long departmentId,
                                @RequestBody ShiftTemplate template) {

        Department dept = departmentService.get(departmentId);
        template.setDepartment(dept);
        return service.create(template);
    }

    @GetMapping("/department/{departmentId}")
    public List<ShiftTemplate> getByDepartment(@PathVariable Long departmentId) {
        return service.getByDepartment(departmentId);
    }
}
