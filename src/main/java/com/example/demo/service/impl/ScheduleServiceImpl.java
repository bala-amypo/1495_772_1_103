package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftRepo;
    private final AvailabilityRepository availabilityRepo;
    private final EmployeeRepository employeeRepo;
    private final GeneratedShiftScheduleRepository scheduleRepo;
    private final DepartmentRepository departmentRepo;

    public ScheduleServiceImpl(ShiftTemplateRepository shiftRepo,
                               AvailabilityRepository availabilityRepo,
                               EmployeeRepository employeeRepo,
                               GeneratedShiftScheduleRepository scheduleRepo,
                               DepartmentRepository departmentRepo) {
        this.shiftRepo = shiftRepo;
        this.availabilityRepo = availabilityRepo;
        this.employeeRepo = employeeRepo;
        this.scheduleRepo = scheduleRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<GeneratedShiftSchedule> result = new ArrayList<>();

        List<EmployeeAvailability> available =
                availabilityRepo.findByAvailableDateAndAvailable(date, true);

        if (available.isEmpty()) return result;

        for (Department dept : departmentRepo.findAll()) {

            for (ShiftTemplate st : shiftRepo.findByDepartment_Id(dept.getId())) {

                for (EmployeeAvailability av : available) {

                    Employee emp = av.getEmployee();

                    if (emp.getSkills().containsAll(st.getRequiredSkills())) {

                        GeneratedShiftSchedule g = new GeneratedShiftSchedule();
                        g.setDepartment(dept);
                        g.setEmployee(emp);
                        g.setShiftTemplate(st);
                        g.setShiftDate(date);

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
