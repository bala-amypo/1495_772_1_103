package com.example.demo.service.impl;

import com.example.demo.model.EmployeeAvailability;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeAvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeAvailabilityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmployeeAvailabilityServiceImpl implements EmployeeAvailabilityService {

    private final EmployeeAvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeAvailabilityServiceImpl(EmployeeAvailabilityRepository availabilityRepository,
                                           EmployeeRepository employeeRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        Employee employee = employeeRepository.findById(availability.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (availabilityRepository.findByEmployee_IdAndAvailableDate(employee.getId(), availability.getAvailableDate()) != null) {
            throw new IllegalArgumentException("Availability already exists for this employee on the given date");
        }
        availability.setEmployee(employee);
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        EmployeeAvailability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        existing.setAvailable(availability.getAvailable());
        existing.setAvailableDate(availability.getAvailableDate());
        return availabilityRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        EmployeeAvailability existing = availabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        availabilityRepository.delete(existing);
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDateAndAvailable(date, true);
    }

    @Override
    public List<EmployeeAvailability> getByEmployee(Long employeeId) {
        return availabilityRepository.findByEmployee_Id(employeeId);
    }
}
