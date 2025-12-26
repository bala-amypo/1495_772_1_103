package com.example.demo.dto;

import java.time.LocalDate;

public class AvailabilityDto {

    private Long employeeId;
    private LocalDate availableDate;
    private Boolean available;

    public AvailabilityDto() {}

   
    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
