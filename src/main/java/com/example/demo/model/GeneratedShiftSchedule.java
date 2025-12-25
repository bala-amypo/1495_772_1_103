package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class GeneratedShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Department department;

    @ManyToOne
    private ShiftTemplate shiftTemplate;

    private LocalDate shiftDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public GeneratedShiftSchedule() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public ShiftTemplate getShiftTemplate() { return shiftTemplate; }
    public void setShiftTemplate(ShiftTemplate shiftTemplate) { this.shiftTemplate = shiftTemplate; }

    public LocalDate getShiftDate() { return shiftDate; }
    public void setShiftDate(LocalDate shiftDate) { this.shiftDate = shiftDate; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
