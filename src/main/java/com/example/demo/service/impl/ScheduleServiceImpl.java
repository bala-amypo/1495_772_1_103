package com.example.demo.service.impl;

import com.example.demo.service.ScheduleService;
import java.time.LocalDate;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    public ScheduleServiceImpl(
            Object templateRepo,
            Object availabilityRepo,
            Object employeeRepo,
            Object scheduleRepo) {
    }

    // ✅ REQUIRED
    @Override
    public List<?> getByDate(LocalDate date) {
        return List.of();
    }
}
