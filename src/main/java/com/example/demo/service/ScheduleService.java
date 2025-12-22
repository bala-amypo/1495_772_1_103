package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.GeneratedShiftScheduleEntity;

public interface ScheduleService {

    GeneratedShiftScheduleEntity save(
            GeneratedShiftScheduleEntity schedule);

    List<GeneratedShiftScheduleEntity> findAll();
}
