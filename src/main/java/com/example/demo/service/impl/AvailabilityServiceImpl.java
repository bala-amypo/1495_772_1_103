package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;

import java.time.LocalDate;
import java.util.List;

public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepo;
    private final EmployeeRepository employeeRepo;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepo,
                                   EmployeeRepository employeeRepo) {
        this.availabilityRepo = availabilityRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        Long empId = availability.getEmployee().getId();
        LocalDate date = availability.getAvailableDate();

        if (date != null &&
            availabilityRepo.findByEmployee_IdAndAvailableDate(empId, date).isPresent()) {
            throw new RuntimeException("Availability already exists");
        }

        return availabilityRepo.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability updated) {
        EmployeeAvailability existing = availabilityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setAvailable(updated.getAvailable());
        existing.setAvailableDate(updated.getAvailableDate());

        return availabilityRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability av = availabilityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        availabilityRepo.delete(av);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepo.findByAvailableDateAndAvailable(date, true);
    }
}
