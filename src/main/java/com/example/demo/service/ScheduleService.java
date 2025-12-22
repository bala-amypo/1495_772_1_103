package com.example.demo.service;

import com.example.demo.model.GeneratedShiftSchedule;
import com.example.demo.repository.GeneratedShiftScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private GeneratedShiftScheduleRepository repository;


    public List<GeneratedShiftSchedule> generateForDate(LocalDate date) {

        List<GeneratedShiftSchedule> generatedList = new ArrayList<>();

       
        String[] employees = {"Ravi", "Arjun", "Meena"};
        String[] skills = {"Java", "Spring", "React"};

        for (int i = 0; i < employees.length; i++) {
            GeneratedShiftSchedule schedule =
                    new GeneratedShiftSchedule(
                            date,
                            "Morning Shift",
                            employees[i],
                            skills[i]
                    );

            generatedList.add(schedule);
        }

        return repository.saveAll(generatedList);
    }

    
    public List<GeneratedShiftSchedule> getByDate(LocalDate date) {
        return repository.findByShiftDate(date);
    }

    
    public List<GeneratedShiftSchedule> getAll() {
        return repository.findAll();
    }

    
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
