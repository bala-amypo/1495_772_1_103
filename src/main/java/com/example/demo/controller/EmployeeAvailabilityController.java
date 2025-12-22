package com.example.demo.controller;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.service.AvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityService service;

    public AvailabilityController(AvailabilityService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeAvailability create(@RequestBody EmployeeAvailability availability) {
        return service.create(availability);
    }

    @PutMapping("/{id}")
    public EmployeeAvailability update(@PathVariable Long id,
                                       @RequestBody EmployeeAvailability availability) {
        return service.update(id, availability);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/date/{date}")
    public List<EmployeeAvailability> getByDate(@PathVariable String date) {
        return service.getByDate(LocalDate.parse(date));
    }
}
