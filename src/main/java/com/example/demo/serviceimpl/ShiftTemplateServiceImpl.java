package com.example.demo.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ShiftTemplateEntity;
import com.example.demo.repository.ShiftTemplateRepository;
import com.example.demo.service.ShiftTemplateService;

@Service
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository repository;

    public ShiftTemplateServiceImpl(ShiftTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShiftTemplateEntity save(ShiftTemplateEntity shift) {

        if (shift.getEndTime().isBefore(shift.getStartTime())) {
            throw new IllegalArgumentException(
                "End time must be after start time"
            );
        }

        return repository.save(shift);
    }

    @Override
    public List<ShiftTemplateEntity> findAll() {
        return repository.findAll();
    }
}
