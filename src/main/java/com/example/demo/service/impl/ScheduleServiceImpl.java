package com.example.demo.service.impl;

import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {

    public ScheduleServiceImpl(
            ShiftTemplateRepository templateRepository,
            AvailabilityRepository availabilityRepository,
            EmployeeRepository employeeRepository,
            GeneratedShiftScheduleRepository scheduleRepository) {
    }
}
