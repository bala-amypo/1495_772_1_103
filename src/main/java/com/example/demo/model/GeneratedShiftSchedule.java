package com.example.demo.model;

import java.time.LocalDate;

public class GeneratedShiftSchedule {

    private Long id;
    private LocalDate shiftDate;
    private Employee employee;
    private ShiftTemplate shiftTemplate;

    public GeneratedShiftSchedule() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getShiftDate() { return shiftDate; }
    public void setShiftDate(LocalDate shiftDate) { this.shiftDate = shiftDate; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public ShiftTemplate getShiftTemplate() { return shiftTemplate; }
    public void setShiftTemplate(ShiftTemplate shiftTemplate) { this.shiftTemplate = shiftTemplate; }
}
