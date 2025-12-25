package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        employee.setId(id);
        return service.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}
