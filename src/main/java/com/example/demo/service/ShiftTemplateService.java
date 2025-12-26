package com.example.demo.service;

import com.example.demo.model.ShiftTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShiftTemplateService {

    ShiftTemplate create(ShiftTemplate shiftTemplate);

    List<ShiftTemplate> getByDepartment(Long departmentId);

    List<ShiftTemplate> getAll();
}
