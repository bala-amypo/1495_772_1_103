package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.EmployeeAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class EmployeeAvailabilityController {

    private final EmployeeAvailabilityService availabilityService;

    public EmployeeAvailabilityController(EmployeeAvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<EmployeeAvailability> createAvailability(@PathVariable Long employeeId,
                                                                   @RequestBody EmployeeAvailability availability) {
        availability.setEmployee(new com.example.demo.model.Employee());
        availability.getEmployee().setId(employeeId);
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAvailability> updateAvailability(@PathVariable Long id,
                                                                   @RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.update(id, availability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> getByDate(@PathVariable String date) {
        return ResponseEntity.ok(availabilityService.getByDate(LocalDate.parse(date)));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeAvailability>> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(availabilityService.getByEmployee(employeeId));
    }
}
