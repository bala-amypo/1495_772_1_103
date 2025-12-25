package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AvailabilityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
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

        Long empId = availability.getEmployee().getId();
        LocalDate date = availability.getAvailableDate();

        if (repo.findByEmployee_IdAndAvailableDate(empId, date).isPresent()) {
            throw new IllegalArgumentException("exists");
        }

        return repo.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability updated) {
        EmployeeAvailability existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        existing.setAvailable(updated.getAvailable());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability av = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        repo.delete(av);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return repo.findByAvailableDateAndAvailable(date, true);
    }
}
