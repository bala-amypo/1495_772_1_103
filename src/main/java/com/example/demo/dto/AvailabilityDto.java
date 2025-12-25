package com.example.demo.dto;

import java.time.LocalDate;

public class AvailabilityDto {
    private LocalDate availableDate;
    private Boolean available;

    public AvailabilityDto() {}

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