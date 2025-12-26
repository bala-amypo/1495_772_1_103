package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityService service;
    private final EmployeeRepository employeeRepository;

    public AvailabilityController(AvailabilityService service,
                                  EmployeeRepository employeeRepository) {
        this.service = service;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(@PathVariable String date) {
        LocalDate d = LocalDate.parse(date);
        return ResponseEntity.ok(service.getByDate(d));
    }

    @PostMapping
    public ResponseEntity<EmployeeAvailability> create(@RequestBody EmployeeAvailability av) {
        return ResponseEntity.ok(service.create(av));
    }
}
