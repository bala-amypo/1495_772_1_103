package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvailabilityDTO {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Available date is required")
    private LocalDate availableDate;

    @NotNull(message = "Availability status is required")
    private Boolean available;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(Long employeeId, LocalDate availableDate, Boolean available) {
        this.employeeId = employeeId;
        this.availableDate = availableDate;
        this.available = available;
    }

    // Getters and Setters
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
