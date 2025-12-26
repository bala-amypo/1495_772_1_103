package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_availability")
public class EmployeeAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate availableDate;
    private boolean available;

    public EmployeeAvailability() {}

    public EmployeeAvailability(Employee employee,
                                LocalDate availableDate,
                                boolean available) {
        this.employee = employee;
        this.availableDate = availableDate;
        this.available = available;
    }

    // ✅ REQUIRED
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() { return employee; }
    public LocalDate getAvailableDate() { return availableDate; }
    public boolean isAvailable() { return available; }
}
