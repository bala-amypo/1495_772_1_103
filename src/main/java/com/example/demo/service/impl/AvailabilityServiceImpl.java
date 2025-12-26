package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository repository;

    public AvailabilityServiceImpl(AvailabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        EmployeeAvailability existing =
                repository.findByEmployee_IdAndAvailableDate(
                        availability.getEmployee().getId(),
                        availability.getAvailableDate());

        if (existing != null) {
            throw new IllegalArgumentException("Availability exists");
        }
        return repository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        EmployeeAvailability existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setAvailable(availability.getAvailable());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability availability = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        repository.delete(availability);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repository.findByAvailableDateAndAvailable(date, true);
    }
}
