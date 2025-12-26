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
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate availableDate;

    private Boolean available;

    public EmployeeAvailability() {}

    public EmployeeAvailability(Employee employee,
                                LocalDate availableDate,
                                Boolean available) {
        this.employee = employee;
        this.availableDate = availableDate;
        this.available = available;
    }

    // getters & setters
}
