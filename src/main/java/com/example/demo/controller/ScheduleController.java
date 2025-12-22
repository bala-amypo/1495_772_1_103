package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.GeneratedShiftScheduleEntity;
import com.example.demo.service.ScheduleService;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public GeneratedShiftScheduleEntity save(
            @RequestBody GeneratedShiftScheduleEntity schedule) {
        return service.save(schedule);
    }

    @GetMapping
    public List<GeneratedShiftScheduleEntity> getAll() {
        return service.findAll();
    }
}
