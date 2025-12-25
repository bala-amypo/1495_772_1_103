package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository repo;
    private final EmployeeRepository employeeRepo;

    public AvailabilityServiceImpl(AvailabilityRepository repo,
                                   EmployeeRepository employeeRepo) {
        this.repo = repo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        repo.findByEmployee_IdAndAvailableDate(
                availability.getEmployee().getId(),
                availability.getAvailableDate()
        ).ifPresent(a -> {
            throw new IllegalArgumentException("Availability exists");
        });

        return repo.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability updated) {
        EmployeeAvailability existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setAvailable(updated.getAvailable());
        existing.setAvailableDate(updated.getAvailableDate());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability av = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        repo.delete(av);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repo.findByAvailableDateAndAvailable(date, true);
    }
}
