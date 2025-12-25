package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {
    private final ShiftTemplateRepository shiftTemplateRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template) {
        if (!template.getEndTime().isAfter(template.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        
        if (shiftTemplateRepository.findByTemplateNameAndDepartment_Id(
                template.getTemplateName(), template.getDepartment().getId()).isPresent()) {
            throw new IllegalArgumentException("Template with name is not unique in department");
        }
        
        return shiftTemplateRepository.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        if (departmentId == null) {
            return shiftTemplateRepository.findAll();
        }
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }
}