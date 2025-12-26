package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AvailabilityRepository
        extends JpaRepository<EmployeeAvailability, Long> {

    Optional<EmployeeAvailability>
    findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
}
