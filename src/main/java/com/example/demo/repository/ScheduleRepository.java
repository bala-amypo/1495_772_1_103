package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.GeneratedShiftScheduleEntity;

public interface ScheduleRepository
        extends JpaRepository<GeneratedShiftScheduleEntity, Long> {
}
