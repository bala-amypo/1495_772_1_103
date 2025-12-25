package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service  
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository repo;

    public AvailabilityServiceImpl(AvailabilityRepository repo) {
        this.repo = repo;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability av) {

        if (av.getEmployee() != null && av.getAvailableDate() != null) {
            Long empId = av.getEmployee().getId();

            repo.findByEmployee_IdAndAvailableDate(empId, av.getAvailableDate())
                .ifPresent(a -> {
                    throw new RuntimeException("Availability already exists");
                });
        }

        return repo.save(av);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability av) {

        EmployeeAvailability old = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        old.setAvailable(av.getAvailable());
        old.setAvailableDate(av.getAvailableDate());

        return repo.save(old);
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
