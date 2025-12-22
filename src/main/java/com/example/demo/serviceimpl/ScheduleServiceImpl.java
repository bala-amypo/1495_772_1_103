package com.example.demo.serviceimpl;

import com.example.demo.entity.GeneratedShiftScheduleEntity;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<GeneratedShiftScheduleEntity> generateForDate(LocalDate date) {
        // TEMP simple logic
        GeneratedShiftScheduleEntity schedule = new GeneratedShiftScheduleEntity();
        schedule.setDate(date);
        schedule.setEmployeeName("Demo Employee");
        schedule.setShiftName("Morning Shift");

        scheduleRepository.save(schedule);
        return scheduleRepository.findByDate(date);
    }

    @Override
    public List<GeneratedShiftScheduleEntity> getByDate(LocalDate date) {
        return scheduleRepository.findByDate(date);
    }

    @Override
    public List<GeneratedShiftScheduleEntity> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
