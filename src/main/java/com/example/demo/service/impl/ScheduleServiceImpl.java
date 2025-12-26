package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ScheduleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScheduleServiceImpl implements ScheduleService {

    private final ShiftTemplateRepository shiftRepo;
    private final AvailabilityRepository availabilityRepo;
    private final EmployeeRepository employeeRepo;
    private final GeneratedShiftScheduleRepository scheduleRepo;
    private final DepartmentRepository deptRepo;

    public ScheduleServiceImpl(ShiftTemplateRepository shiftRepo,
                               AvailabilityRepository availabilityRepo,
                               EmployeeRepository employeeRepo,
                               GeneratedShiftScheduleRepository scheduleRepo,
                               DepartmentRepository deptRepo) {
        this.shiftRepo = shiftRepo;
        this.availabilityRepo = availabilityRepo;
        this.employeeRepo = employeeRepo;
        this.scheduleRepo = scheduleRepo;
        this.deptRepo = deptRepo;
    }

    @Override
    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {
        List<GeneratedShiftSchedule> result = new ArrayList<>();

        List<Department> departments = deptRepo.findAll();
        List<EmployeeAvailability> available =
                availabilityRepo.findByAvailableDateAndAvailable(date, true);

        for (Department dept : departments) {
            List<ShiftTemplate> shifts = shiftRepo.findByDepartment_Id(dept.getId());

            for (ShiftTemplate shift : shifts) {
                for (EmployeeAvailability av : available) {
                    Employee emp = av.getEmployee();

                    Set<String> empSkills = emp.getSkills();
                    Set<String> shiftSkills = shift.getRequiredSkills();

                    if (empSkills.containsAll(shiftSkills)) {
                        GeneratedShiftSchedule g = new GeneratedShiftSchedule();
                        g.setShiftDate(date);
                        g.setEmployee(emp);
                        g.setShiftTemplate(shift);

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
