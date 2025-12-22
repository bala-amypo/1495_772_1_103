package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ShiftTemplateEntity;

public interface ShiftTemplateService {

    ShiftTemplateEntity save(ShiftTemplateEntity shift);

    List<ShiftTemplateEntity> findAll();
}
