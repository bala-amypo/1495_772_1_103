package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.model.Department;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.ShiftTemplateService;

import java.util.List;

public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftRepo;
    private final DepartmentRepository deptRepo;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftRepo,
                                    DepartmentRepository deptRepo) {
        this.shiftRepo = shiftRepo;
        this.deptRepo = deptRepo;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate shiftTemplate) {
        Department dept = deptRepo.findById(
                shiftTemplate.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (!shiftTemplate.getEndTime().isAfter(shiftTemplate.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        if (shiftRepo.findByTemplateNameAndDepartment_Id(
                shiftTemplate.getTemplateName(), dept.getId()).isPresent()) {
            throw new RuntimeException("Shift template must be unique");
        }

        return shiftRepo.save(shiftTemplate);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return shiftRepo.findByDepartment_Id(departmentId);
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return shiftRepo.findAll();
    }
}
