package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository;

    public AvailabilityController(AvailabilityService availabilityService,
                                  EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<List<EmployeeAvailability>> byDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return ResponseEntity.ok(availabilityService.getByDate(d));
    }

    public ResponseEntity<EmployeeAvailability> create(EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    public ResponseEntity<EmployeeAvailability> update(Long id, EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.update(id, availability));
    }

    public ResponseEntity<String> delete(Long id) {
        availabilityService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
