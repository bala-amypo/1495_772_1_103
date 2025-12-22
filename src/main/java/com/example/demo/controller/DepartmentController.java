package com.example.employee.controller;

import com.example.employee.entity.Department;
import com.example.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Department create(@RequestBody Department department) {
        return service.create(department);
    }

    // READ ALL
    @GetMapping
    public List<Department> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Department get(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Department update(
            @PathVariable Long id,
            @RequestBody Department department) {
        return service.update(id, department);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Department deleted successfully";
    }
}
