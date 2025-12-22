package com.example.employee.serviceimpl;

import com.example.employee.entity.ShiftTemplateEntity;
import com.example.employee.repository.ShiftTemplateRepository;
import com.example.employee.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository repository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShiftTemplateEntity create(ShiftTemplateEntity template) {

        LocalTime start = template.getStartTime();
        LocalTime end = template.getEndTime();

        if (start == null || end == null || !start.isBefore(end)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        boolean exists =
                repository.existsByDepartmentIdAndShiftNameAndStartTimeAndEndTime(
                        template.getDepartmentId(),
                        template.getShiftName(),
                        start,
                        end
                );

        if (exists) {
            throw new IllegalArgumentException("Shift template already exists");
        }

        return repository.save(template);
    }

    @Override
    public List<ShiftTemplateEntity> getByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }
}
