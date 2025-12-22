package com.example.demo.repository;

import com.example.demo.model.ShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplateEntity, Long> {

    List<ShiftTemplate> findByDepartmentId(Long departmentId);

    boolean existsByDepartmentIdAndShiftNameAndStartTimeAndEndTime(
            Long departmentId,
            String shiftName,
            LocalTime startTime,
            LocalTime endTime
    );
}
