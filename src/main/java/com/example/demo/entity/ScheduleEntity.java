package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "generated_shift_schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Long employeeId;

    private Long shiftTemplateId;

    private String shiftName;

    // ✅ CORRECT NO-ARG CONSTRUCTOR
    public ScheduleEntity() {
    }

    // ✅ CORRECT PARAMETERIZED CONSTRUCTOR
    public ScheduleEntity(LocalDate date, Long employeeId,
                          Long shiftTemplateId, String shiftName) {
        this.date = date;
        this.employeeId = employeeId;
        this.shiftTemplateId = shiftTemplateId;
        this.shiftName = shiftName;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getShiftTemplateId() {
        return shiftTemplateId;
    }

    public void setShiftTemplateId(Long shiftTemplateId) {
        this.shiftTemplateId = shiftTemplateId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }
}
