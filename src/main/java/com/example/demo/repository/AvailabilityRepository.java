package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {

    EmployeeAvailability findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);

    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);

    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
}
