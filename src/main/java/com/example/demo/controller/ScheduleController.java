package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping("/generate/{date}")
    public List<GeneratedShiftSchedule> generate(@PathVariable LocalDate date) {
        return service.generateForDate(date);
    }

    @GetMapping("/date/{date}")
    public List<GeneratedShiftSchedule> getByDate(@PathVariable LocalDate date) {
        return service.getByDate(date);
    }
}
