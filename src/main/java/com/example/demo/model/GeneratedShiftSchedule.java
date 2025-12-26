package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class GeneratedShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate shiftDate;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private ShiftTemplate shiftTemplate;

    @ManyToOne
    private Department department;

    public GeneratedShiftSchedule() {}

    /* ----------------- Getters / Setters ----------------- */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getShiftDate() { return shiftDate; }
    public void setShiftDate(LocalDate shiftDate) { this.shiftDate = shiftDate; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public ShiftTemplate getShiftTemplate() { return shiftTemplate; }
    public void setShiftTemplate(ShiftTemplate shiftTemplate) { this.shiftTemplate = shiftTemplate; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
