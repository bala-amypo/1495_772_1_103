package com.example.demo.service.impl;

import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // Important! This registers the service as a Spring Bean
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository repo;
    private final DepartmentRepository deptRepo;

    public ShiftTemplateServiceImpl(
            ShiftTemplateRepository repo,
            DepartmentRepository deptRepo
    ) {
        this.repo = repo;
        this.deptRepo = deptRepo;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate st) {
        if (st.getEndTime().isBefore(st.getStartTime()))
            throw new RuntimeException("End time must be after start time");

        Long deptId = st.getDepartment().getId();

        // Ensure department exists
        deptRepo.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Ensure unique template name per department
        if (repo.findByTemplateNameAndDepartment_Id(st.getTemplateName(), deptId).isPresent())
            throw new RuntimeException("Template must be unique");

        return repo.save(st);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return repo.findByDepartment_Id(departmentId);
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return repo.findAll();
    }
}
