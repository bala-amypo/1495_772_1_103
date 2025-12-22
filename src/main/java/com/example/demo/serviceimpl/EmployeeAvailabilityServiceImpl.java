package com.example.demo.serviceimpl;

import com.example.demo.entity.EmployeeAvailabilityEntity;
import com.example.demo.repository.EmployeeAvailabilityRepository;
import com.example.demo.service.EmployeeAvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeAvailabilityServiceImpl
        implements EmployeeAvailabilityService {

    private final EmployeeAvailabilityRepository repository;

    public EmployeeAvailabilityServiceImpl(
            EmployeeAvailabilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeAvailabilityEntity save(
            EmployeeAvailabilityEntity availability) {
        return repository.save(availability);
    }

    @Override
    public EmployeeAvailabilityEntity update(
            Long id, EmployeeAvailabilityEntity availability) {

        EmployeeAvailabilityEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        existing.setEmployeeId(availability.getEmployeeId());
        existing.setDate(availability.getDate());
        existing.setAvailable(availability.isAvailable());

        return repository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<EmployeeAvailabilityEntity> getByDate(LocalDate date) {
        return repository.findByDate(date);
    }
}
