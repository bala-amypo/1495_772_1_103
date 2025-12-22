package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeEntity save(@RequestBody EmployeeEntity emp) {
        return service.save(emp);
    }

    @GetMapping
    public List<EmployeeEntity> getAll() {
        return service.findAll();
    }
}
