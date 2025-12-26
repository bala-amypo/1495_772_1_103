package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String role;
    private String skills;
    private Integer maxWeeklyHours;

    private LocalDateTime createdAt;

    public Employee() {}

    // ✅ REQUIRED BY TESTS
    public Employee(String fullName, String email, String role, String skills, int maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getSkills() { return skills; }
    public Integer getMaxWeeklyHours() { return maxWeeklyHours; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setMaxWeeklyHours(Integer maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
