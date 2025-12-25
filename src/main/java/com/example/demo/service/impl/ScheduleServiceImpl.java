package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository templateRepo;
    private final AvailabilityRepository availabilityRepo;
    private final EmployeeRepository employeeRepo;
    private final GeneratedShiftScheduleRepository scheduleRepo;
    private final DepartmentRepository departmentRepo;

    public ScheduleServiceImpl(ShiftTemplateRepository templateRepo,
                               AvailabilityRepository availabilityRepo,
                               EmployeeRepository employeeRepo,
                               GeneratedShiftScheduleRepository scheduleRepo,
                               DepartmentRepository departmentRepo) {
        this.templateRepo = templateRepo;
        this.availabilityRepo = availabilityRepo;
        this.employeeRepo = employeeRepo;
        this.scheduleRepo = scheduleRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<GeneratedShiftSchedule> result = new ArrayList<>();

        List<Department> departments = departmentRepo.findAll();
        List<EmployeeAvailability> available =
                availabilityRepo.findByAvailableDateAndAvailable(date, true);

        for (Department dept : departments) {
            List<ShiftTemplate> templates =
                    templateRepo.findByDepartment_Id(dept.getId());

            for (ShiftTemplate template : templates) {
                Set<String> required = template.getRequiredSkills();

                for (EmployeeAvailability av : available) {
                    Employee emp = av.getEmployee();
                    if (emp.getSkills().containsAll(required)) {

                        GeneratedShiftSchedule g = new GeneratedShiftSchedule();
                        g.setShiftDate(date);
                        g.setStartTime(template.getStartTime());
                        g.setEndTime(template.getEndTime());
                        g.setDepartment(dept);
                        g.setEmployee(emp);
                        g.setShiftTemplate(template);

                        result.add(scheduleRepo.save(g));
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return scheduleRepo.findByShiftDate(date);
    }
}
