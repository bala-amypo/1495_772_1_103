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

    private final ShiftTemplateRepository repo;
    private final DepartmentRepository departmentRepo;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository repo,
                                   DepartmentRepository departmentRepo) {
        this.repo = repo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template) {

        if (!template.getEndTime().isAfter(template.getStartTime())) {
            throw new IllegalArgumentException("after");
        }

        Long deptId = template.getDepartment().getId();
        departmentRepo.findById(deptId)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (repo.findByTemplateNameAndDepartment_Id(
                template.getTemplateName(), deptId).isPresent()) {
            throw new IllegalArgumentException("unique");
        }

        return repo.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return repo.findByDepartment_Id(departmentId);
    }
}
