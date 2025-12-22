package com.example.demo.controller;

import com.example.demo.entity.EmployeeAvailabilityEntity;
import com.example.demo.service.EmployeeAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class EmployeeAvailabilityController {

    private final EmployeeAvailabilityService service;

    public EmployeeAvailabilityController(
            EmployeeAvailabilityService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeAvailabilityEntity create(
            @RequestBody EmployeeAvailabilityEntity availability) {
        return service.save(availability);
    }

    @PutMapping("/{id}")
    public EmployeeAvailabilityEntity update(
            @PathVariable Long id,
            @RequestBody EmployeeAvailabilityEntity availability) {
        return service.update(id, availability);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/date/{date}")
    public List<EmployeeAvailabilityEntity> getByDate(
            @PathVariable String date) {
        return service.getByDate(LocalDate.parse(date));
    }
}
