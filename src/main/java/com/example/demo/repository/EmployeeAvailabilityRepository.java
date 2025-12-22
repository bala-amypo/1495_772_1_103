package com.example.demo.repository;

import com.example.demo.entity.EmployeeAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeAvailabilityRepository
        extends JpaRepository<EmployeeAvailabilityEntity, Long> {

    List<EmployeeAvailabilityEntity> findByDate(LocalDate date);
}
