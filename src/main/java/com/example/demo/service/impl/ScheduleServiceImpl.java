package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.AvailabilityRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.GeneratedShiftScheduleRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository;
    private final GeneratedShiftScheduleRepository generatedShiftScheduleRepository;

    public ScheduleServiceImpl(ShiftTemplateRepository shiftTemplateRepository,
                               AvailabilityRepository availabilityRepository,
                               EmployeeRepository employeeRepository,
                               GeneratedShiftScheduleRepository generatedShiftScheduleRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
        this.generatedShiftScheduleRepository = generatedShiftScheduleRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<ShiftTemplate> templates = shiftTemplateRepository.findAll();
        List<Employee> employees = employeeRepository.findAll(); // Filter available employees as per availability

        List<GeneratedShiftSchedule> schedules = new ArrayList<>();

        for (ShiftTemplate template : templates) {
            for (Employee e : employees) {
                // Basic skill match
                String templateSkills = template.getRequiredSkills();
                String employeeSkills = e.getSkills();
                if (templateSkills != null && employeeSkills != null && employeeSkills.contains(templateSkills)) {

                    GeneratedShiftSchedule g = new GeneratedShiftSchedule();
                    g.setShiftDate(date);
                    g.setStartTime(template.getStartTime());
                    g.setEndTime(template.getEndTime());
                    g.setShiftTemplate(template); // fixed
                    g.setEmployee(e);
                    g.setDepartment(template.getDepartment());

                    generatedShiftScheduleRepository.save(g);
                    schedules.add(g);
                    break; // assign first matching employee only
                }
            }
        }

        return schedules;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return generatedShiftScheduleRepository.findByShiftDate(date);
    }
}
