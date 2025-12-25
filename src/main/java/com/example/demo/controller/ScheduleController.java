package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
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

    @PostMapping("/generate/{date}")
    public List<GeneratedShiftSchedule> generate(@PathVariable String date) {
        return service.generateForDate(LocalDate.parse(date));
    }

    @GetMapping("/{date}")
    public List<GeneratedShiftSchedule> getByDate(@PathVariable String date) {
        return service.getByDate(LocalDate.parse(date));
    }
}
