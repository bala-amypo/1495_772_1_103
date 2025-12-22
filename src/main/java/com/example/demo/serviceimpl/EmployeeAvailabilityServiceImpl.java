package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository repository;

    public AvailabilityServiceImpl(AvailabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        return repository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        EmployeeAvailability existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setEmployeeId(availability.getEmployeeId());
        existing.setDate(availability.getDate());
        existing.setAvailable(availability.isAvailable());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repository.findByDate(date);
    }
}
