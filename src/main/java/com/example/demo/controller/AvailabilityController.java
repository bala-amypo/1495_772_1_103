package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService service;
    private final EmployeeRepository employeeRepository;

    public AvailabilityController(AvailabilityService service,
                                  EmployeeRepository employeeRepository) {
        this.service = service;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<EmployeeAvailability> create(
            @PathVariable Long employeeId,
            @RequestBody EmployeeAvailability availability) {

        availability.setEmployee(
                employeeRepository.findById(employeeId).orElse(null)
        );
        return ResponseEntity.ok(service.create(availability));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(
            @PathVariable String date) {
        return ResponseEntity.ok(service.getByDate(LocalDate.parse(date)));
    }
}
