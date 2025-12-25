package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository generatedShiftScheduleRepository;

    public ScheduleServiceImpl(ShiftTemplateRepository shiftTemplateRepository,
                               EmployeeRepository employeeRepository,
                               GeneratedShiftScheduleRepository generatedShiftScheduleRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.employeeRepository = employeeRepository;
        this.generatedShiftScheduleRepository = generatedShiftScheduleRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<ShiftTemplate> templates = shiftTemplateRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();

        // simple example: assign first employee to first template
        GeneratedShiftSchedule g = new GeneratedShiftSchedule();
        g.setShiftDate(date);
        g.setStartTime(templates.get(0).getStartTime());
        g.setEndTime(templates.get(0).getEndTime());
        g.setEmployee(employees.get(0));
        g.setDepartment(templates.get(0).getDepartment());
        g.setShiftTemplate(templates.get(0)); // make sure setter exists in entity
        generatedShiftScheduleRepository.save(g);

        return generatedShiftScheduleRepository.findByShiftDate(date);
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return generatedShiftScheduleRepository.findByShiftDate(date);
    }
}
