package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;

import java.time.LocalDate;
import java.util.List;

public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository repo;
    private final EmployeeRepository empRepo;

    public AvailabilityServiceImpl(AvailabilityRepository repo, EmployeeRepository empRepo) {
        this.repo = repo;
        this.empRepo = empRepo;
    }

    public EmployeeAvailability create(EmployeeAvailability av) {
        if (av.getEmployee() != null && av.getAvailableDate() != null) {
            Long empId = av.getEmployee().getId();
            if (repo.findByEmployee_IdAndAvailableDate(empId, av.getAvailableDate()).isPresent())
                throw new RuntimeException("Availability already exists");
        }
        return repo.save(av);
    }

    public EmployeeAvailability update(Long id, EmployeeAvailability av) {
        EmployeeAvailability old = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        old.setAvailable(av.getAvailable());
        old.setAvailableDate(av.getAvailableDate());

        return repo.save(old);
    }

    public void delete(Long id) {
        EmployeeAvailability av = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        repo.delete(av);
    }

    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repo.findByAvailableDateAndAvailable(date, true);
    }
}
