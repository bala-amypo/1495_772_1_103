package com.example.demo.repository;

import com.example.demo.model.GeneratedShiftSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository
        extends JpaRepository<ScheduleEntity, Long> {

    List<GeneratedShiftSchedule> findByShiftDate(LocalDate shiftDate);
}
