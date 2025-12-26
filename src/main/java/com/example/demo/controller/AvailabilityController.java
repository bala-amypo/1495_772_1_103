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

    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository;

    public AvailabilityController(AvailabilityService availabilityService,
                                  EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(
            @PathVariable String date) {
        return ResponseEntity.ok(
                availabilityService.getByDate(LocalDate.parse(date))
        );
    }

    @PostMapping
    public ResponseEntity<EmployeeAvailability> create(
            @RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAvailability> update(
            @PathVariable Long id,
            @RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.update(id, availability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        availabilityService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
