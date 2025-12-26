package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping("/generate/{date}")
    public ResponseEntity<List<GeneratedShiftSchedule>> generate(@PathVariable String date) {
        return ResponseEntity.ok(
                service.generateForDate(LocalDate.parse(date))
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<GeneratedShiftSchedule>> byDate(@PathVariable String date) {
        return ResponseEntity.ok(
                service.getByDate(LocalDate.parse(date))
        );
    }
}
