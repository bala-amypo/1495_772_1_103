package com.example.demo.repository;

import com.example.demo.model.EmployeeAvailability;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository {

    EmployeeAvailability save(EmployeeAvailability availability);

    Optional<EmployeeAvailability> findById(Long id);

    Optional<EmployeeAvailability> findByEmployee_IdAndAvailableDate(
            Long employeeId, LocalDate date
    );

    List<EmployeeAvailability> findByAvailableDateAndAvailable(
            LocalDate date, boolean available
    );

    List<EmployeeAvailability> findByEmployee_Id(Long employeeId);

    void delete(EmployeeAvailability availability);
}
