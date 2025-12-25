package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;

import java.time.LocalDate;
import java.util.*;

public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository templateRepo;
    private final AvailabilityRepository availabilityRepo;
    private final EmployeeRepository employeeRepo;
    private final GeneratedShiftScheduleRepository scheduleRepo;
    private final DepartmentRepository departmentRepo;

    public ScheduleServiceImpl(
            ShiftTemplateRepository templateRepo,
            AvailabilityRepository availabilityRepo,
            EmployeeRepository employeeRepo,
            GeneratedShiftScheduleRepository scheduleRepo,
            DepartmentRepository departmentRepo
    ) {
        this.templateRepo = templateRepo;
        this.availabilityRepo = availabilityRepo;
        this.employeeRepo = employeeRepo;
        this.scheduleRepo = scheduleRepo;
        this.departmentRepo = departmentRepo;
    }

    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<GeneratedShiftSchedule> result = new ArrayList<>();

        for (Department dept : departmentRepo.findAll()) {
            List<ShiftTemplate> templates = templateRepo.findByDepartment_Id(dept.getId());
            List<EmployeeAvailability> availabilities =
                    availabilityRepo.findByAvailableDateAndAvailable(date, true);

            for (ShiftTemplate st : templates) {
                for (EmployeeAvailability av : availabilities) {
                    Employee e = av.getEmployee();

                    if (e.getSkills().contains(st.getRequiredSkills())) {
                        GeneratedShiftSchedule g = new GeneratedShiftSchedule();
                        g.setEmployee(e);
                        g.setShiftTemplate(st);
                        g.setShiftDate(date);
                        result.add(scheduleRepo.save(g));
                    }
                }
            }
        }
        return result;
    }

    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepo.findByShiftDate(date);
    }
}
