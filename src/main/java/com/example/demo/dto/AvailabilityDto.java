package com.example.demo.dto;

import java.time.LocalDate;

public class AvailabilityDto {

    private Long employeeId;
    private LocalDate date;
    private Boolean available;

    public AvailabilityDto() {
    }

    public AvailabilityDto(Long employeeId, LocalDate date, Boolean available) {
        this.employeeId = employeeId;
        this.date = date;
        this.available = available;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
