package com.example.demo.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.GeneratedShiftScheduleEntity;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public GeneratedShiftScheduleEntity save(
            GeneratedShiftScheduleEntity schedule) {
        return repository.save(schedule);
    }

    @Override
    public List<GeneratedShiftScheduleEntity> findAll() {
        return repository.findAll();
    }
}
