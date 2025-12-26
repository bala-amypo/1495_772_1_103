package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository templateRepository;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository templateRepository,
                                    DepartmentRepository departmentRepository) {
        this.templateRepository = templateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template) {
        if (!template.getEndTime().isAfter(template.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        if (templateRepository.findByTemplateNameAndDepartment_Id(
                template.getTemplateName(),
                template.getDepartment().getId()) != null) {
            throw new IllegalArgumentException("Template exists");
        }

        return templateRepository.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return templateRepository.findByDepartment_Id(departmentId);
    }
}
