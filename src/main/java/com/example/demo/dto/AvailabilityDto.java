package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvailabilityDTO {

    @NotNull
    private Long employeeId;

    @NotNull
    private LocalDate availableDate;

    @NotNull
    private Boolean available;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(Long employeeId, LocalDate availableDate, Boolean available) {
        this.employeeId = employeeId;
        this.availableDate = availableDate;
        this.available = available;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
