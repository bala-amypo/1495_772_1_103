package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.GeneratedShiftScheduleRepository;
import com.example.demo.repository.ShiftTemplateRepository;
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

    public ScheduleServiceImpl(
            ShiftTemplateRepository shiftTemplateRepository,
            EmployeeRepository employeeRepository,
            GeneratedShiftScheduleRepository generatedShiftScheduleRepository
    ) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.employeeRepository = employeeRepository;
        this.generatedShiftScheduleRepository = generatedShiftScheduleRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<ShiftTemplate> templates = shiftTemplateRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();

        if (templates.isEmpty()) {
            throw new IllegalStateException("No shift templates found");
        }

        if (employees.isEmpty()) {
            throw new IllegalStateException("No employees found");
        }

        ShiftTemplate template = templates.get(0);
        Employee employee = employees.get(0);

        if (template.getDepartment() == null) {
            throw new IllegalStateException("Shift template has no department");
        }

        GeneratedShiftSchedule schedule = new GeneratedShiftSchedule();
        schedule.setShiftDate(date);
        schedule.setStartTime(template.getStartTime());
        schedule.setEndTime(template.getEndTime());
        schedule.setEmployee(employee);
        schedule.setDepartment(template.getDepartment());
        schedule.setShiftTemplate(template);

        generatedShiftScheduleRepository.save(schedule);

        return generatedShiftScheduleRepository.findByShiftDate(date);
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return generatedShiftScheduleRepository.findByShiftDate(date);
    }
}
