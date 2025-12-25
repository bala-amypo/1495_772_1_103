package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee e) {
        return service.createEmployee(e);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return service.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee e) {
        return service.updateEmployee(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    @GetMapping("/email/{email}")
    public Employee getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}
