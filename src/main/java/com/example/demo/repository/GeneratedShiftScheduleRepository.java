package com.example.demo.repository;

import com.example.demo.model.GeneratedShiftSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GeneratedShiftScheduleRepository extends JpaRepository<GeneratedShiftSchedule, Long> {
    List<GeneratedShiftSchedule> findByShiftDate(LocalDate date);
    List<GeneratedShiftSchedule> findByEmployee_Id(Long employeeId);
    GeneratedShiftSchedule findByShiftDateAndEmployee_Id(LocalDate date, Long employeeId);
}
