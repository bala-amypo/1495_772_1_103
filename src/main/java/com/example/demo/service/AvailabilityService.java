package com.example.demo.service;

import com.example.demo.model.EmployeeAvailability;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityService {

    EmployeeAvailability create(EmployeeAvailability av);

    EmployeeAvailability update(Long id, EmployeeAvailability av);

    void delete(Long id);

    List<EmployeeAvailability> getByDate(LocalDate date);
}
