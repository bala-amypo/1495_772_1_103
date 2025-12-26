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

    // ✅ REQUIRED
    public EmployeeAvailability(Employee employee, LocalDate availableDate, boolean available) {
        this.employee = employee;
        this.availableDate = availableDate;
        this.available = available;
    }
}
