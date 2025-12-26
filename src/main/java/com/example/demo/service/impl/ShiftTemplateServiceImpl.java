package com.example.demo.service.impl;

import com.example.demo.model.Department;
import com.example.demo.model.ShiftTemplate;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository repository;
    private final DepartmentRepository departmentRepository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository repository,
                                    DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate st) {

        Department dept = departmentRepository.findById(st.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (!st.getEndTime().isAfter(st.getStartTime()))
            throw new RuntimeException("End time must be after start time");

        repository.findByTemplateNameAndDepartment_Id(
                st.getTemplateName(), dept.getId()
        ).ifPresent(x -> {
            throw new RuntimeException("Shift template must be unique");
        });

        st.setDepartment(dept);
        return repository.save(st);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        return repository.findByDepartment_Id(departmentId);
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return repository.findAll();
    }
}
