package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import com.example.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService service;
    private final EmployeeService employeeService;

    public AvailabilityController(AvailabilityService service,
                                  EmployeeService employeeService) {
        this.service = service;
        this.employeeService = employeeService;
    }

    @PostMapping("/{employeeId}")
    public EmployeeAvailability create(@PathVariable Long employeeId,
                                       @RequestBody EmployeeAvailability availability) {

        Employee employee = employeeService.getEmployee(employeeId);
        availability.setEmployee(employee);
        return service.create(availability);
    }

    @GetMapping("/date/{date}")
    public List<EmployeeAvailability> getByDate(@PathVariable LocalDate date) {
        return service.getByDate(date);
    }
}
