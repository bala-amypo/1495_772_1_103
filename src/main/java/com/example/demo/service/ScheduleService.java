package com.example.demo.service;

import com.example.demo.model.GeneratedShiftSchedule;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ScheduleService {

    List<GeneratedShiftSchedule> generateForDate(LocalDate date);

    List<GeneratedShiftSchedule> getByDate(LocalDate date);
}
