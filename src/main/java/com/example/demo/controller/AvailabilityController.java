package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<EmployeeAvailability> create(@PathVariable Long employeeId, @RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(availabilityService.getByDate(localDate));
    }
}