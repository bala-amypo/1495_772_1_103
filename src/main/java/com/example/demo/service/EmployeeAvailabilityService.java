package com.example.demo.service;

import com.example.demo.entity.EmployeeAvailabilityEntity;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeAvailabilityService {

    EmployeeAvailabilityEntity save(EmployeeAvailabilityEntity availability);

    EmployeeAvailabilityEntity update(
            Long id, EmployeeAvailabilityEntity availability);

    void deleteById(Long id);

    List<EmployeeAvailabilityEntity> getByDate(LocalDate date);
}
