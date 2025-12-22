package com.example.demo.repository;

import com.example.demo.entity.GeneratedShiftScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository
        extends JpaRepository<GeneratedShiftScheduleEntity, Long> {

    List<GeneratedShiftScheduleEntity> findByDate(LocalDate date);
}
