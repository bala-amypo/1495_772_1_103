package com.example.demo.controller;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/generate/{date}")
    public List<GeneratedShiftSchedule> generateForDate(
            @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        return scheduleService.generateForDate(localDate);
    }

  
    @GetMapping("/date/{date}")
    public List<GeneratedShiftSchedule> getByDate(
            @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        return scheduleService.getByDate(localDate);
    }

   
    @GetMapping
    public List<GeneratedShiftSchedule> getAll() {
        return scheduleService.getAll();
    }

   
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return "Schedule deleted successfully";
    }
}
