package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeAvailabilityRepository extends JpaRepository<EmployeeAvailability, Long> {
    EmployeeAvailability findByEmployee_IdAndAvailableDate(Long employeeId, LocalDate date);
    List<EmployeeAvailability> findByAvailableDateAndAvailable(LocalDate date, boolean available);
    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);
}
