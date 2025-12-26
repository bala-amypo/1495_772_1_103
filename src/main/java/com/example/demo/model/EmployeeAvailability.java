package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(
    name = "employee_availability",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "availableDate"})
)
public class EmployeeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @NotNull
    private LocalDate availableDate;

    @NotNull
    private Boolean available;

    public EmployeeAvailability() {}

    public Long getId() { return id; }
    public Employee getEmployee() { return employee; }
    public LocalDate getAvailableDate() { return availableDate; }
    public Boolean getAvailable() { return available; }

    public void setId(Long id) { this.id = id; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    public void setAvailable(Boolean available) { this.available = available; }
}
