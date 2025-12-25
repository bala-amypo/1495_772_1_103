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
    public EmployeeAvailability create(@RequestBody EmployeeAvailability av) {
        return service.create(av);
    }

    @PutMapping("/{id}")
    public EmployeeAvailability update(@PathVariable Long id, @RequestBody EmployeeAvailability av) {
        return service.update(id, av);
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
