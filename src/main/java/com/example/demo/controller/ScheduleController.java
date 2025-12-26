package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public ResponseEntity<List<GeneratedShiftSchedule>> generate(String date) {
        LocalDate d = LocalDate.parse(date);
        return ResponseEntity.ok(scheduleService.generateForDate(d));
    }

    public ResponseEntity<List<GeneratedShiftSchedule>> byDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return ResponseEntity.ok(scheduleService.getByDate(d));
    }
}
