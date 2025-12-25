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
    private final AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        if (availabilityRepository.findByEmployee_IdAndAvailableDate(
                availability.getEmployee().getId(), availability.getAvailableDate()).isPresent()) {
            throw new IllegalArgumentException("Availability record already exists for this employee and date");
        }
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        if (id != null) {
            EmployeeAvailability existing = availabilityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Availability record not found"));
            existing.setAvailable(availability.getAvailable());
            return availabilityRepository.save(existing);
        }
        throw new IllegalArgumentException("ID cannot be null");
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            EmployeeAvailability availability = availabilityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Availability record not found"));
            if (availability != null) {
                availabilityRepository.delete(availability);
            }
        }
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDateAndAvailable(date, true);
    }
}