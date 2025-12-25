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
    private final ShiftTemplateRepository shiftTemplateRepository;
    private final AvailabilityRepository availabilityRepository;
    private final GeneratedShiftScheduleRepository scheduleRepository;
    private final DepartmentRepository departmentRepository;

    public ScheduleServiceImpl(ShiftTemplateRepository shiftTemplateRepository,
                             AvailabilityRepository availabilityRepository,
                             EmployeeRepository employeeRepository,
                             GeneratedShiftScheduleRepository scheduleRepository,
                             DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.availabilityRepository = availabilityRepository;
        this.scheduleRepository = scheduleRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<Department> departments = departmentRepository.findAll();
        List<EmployeeAvailability> availableEmployees = availabilityRepository.findByAvailableDateAndAvailable(date, true);
        List<GeneratedShiftSchedule> schedules = new ArrayList<>();

        for (Department department : departments) {
            List<ShiftTemplate> templates = shiftTemplateRepository.findByDepartment_Id(department.getId());
            
            for (ShiftTemplate template : templates) {
                for (EmployeeAvailability availability : availableEmployees) {
                    Employee employee = availability.getEmployee();
                    if (hasMatchingSkills(employee.getSkills(), template.getRequiredSkills())) {
                        GeneratedShiftSchedule schedule = new GeneratedShiftSchedule(
                                date,
                                template.getStartTime(),
                                template.getEndTime(),
                                template,
                                department,
                                employee
                        );
                        schedules.add(scheduleRepository.save(schedule));
                        break; // Assign first qualified employee
                    }
                }
            }
        }
        return schedules;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepository.findByShiftDate(date);
    }

    private boolean hasMatchingSkills(String employeeSkills, String requiredSkills) {
        if (employeeSkills == null || requiredSkills == null) return false;
        String[] empSkills = employeeSkills.toLowerCase().split(",");
        String[] reqSkills = requiredSkills.toLowerCase().split(",");
        
        for (String reqSkill : reqSkills) {
            boolean found = false;
            for (String empSkill : empSkills) {
                if (empSkill.trim().equals(reqSkill.trim())) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}