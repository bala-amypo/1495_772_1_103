package com.example.employee.service;

import com.example.employee.entity.ShiftTemplateEntity;

import java.util.List;

public interface ShiftTemplateService {

    ShiftTemplateEntity create(ShiftTemplateEntity template);

    List<ShiftTemplateEntity> getByDepartment(Long departmentId);
}
