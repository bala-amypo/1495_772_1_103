package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository repository;
    private final EmployeeRepository employeeRepository;

    public AvailabilityServiceImpl(AvailabilityRepository repository,
                                   EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability av) {

        if (av.getEmployee() != null && av.getAvailableDate() != null) {
            repository.findByEmployee_IdAndAvailableDate(
                    av.getEmployee().getId(),
                    av.getAvailableDate()
            ).ifPresent(x -> {
                throw new RuntimeException("Availability exists");
            });
        }

        return repository.save(av);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability updated) {
        EmployeeAvailability existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        if (updated.getAvailable() != null)
            existing.setAvailable(updated.getAvailable());

        if (updated.getAvailableDate() != null)
            existing.setAvailableDate(updated.getAvailableDate());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability av = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        repository.delete(av);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repository.findByAvailableDateAndAvailable(date, true);
    }
}
