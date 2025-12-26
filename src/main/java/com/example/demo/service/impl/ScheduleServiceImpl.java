package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository templateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ShiftTemplateRepository templateRepository,
                               AvailabilityRepository availabilityRepository,
                               EmployeeRepository employeeRepository,
                               GeneratedShiftScheduleRepository scheduleRepository) {
        this.templateRepository = templateRepository;
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<ShiftTemplate> templates = templateRepository.findAll();
        List<EmployeeAvailability> available =
                availabilityRepository.findByAvailableDateAndAvailable(date, true);

        List<GeneratedShiftSchedule> result = new ArrayList<>();

        for (ShiftTemplate template : templates) {
            for (EmployeeAvailability ea : available) {
                Employee emp = ea.getEmployee();

                GeneratedShiftSchedule schedule = new GeneratedShiftSchedule();
                schedule.setShiftDate(date);
                schedule.setStartTime(template.getStartTime());
                schedule.setEndTime(template.getEndTime());
                schedule.setDepartment(template.getDepartment());
                schedule.setShiftTemplate(template);
                schedule.setEmployee(emp);

                result.add(scheduleRepository.save(schedule));
                break;
            }
        }
        return result;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }
}
