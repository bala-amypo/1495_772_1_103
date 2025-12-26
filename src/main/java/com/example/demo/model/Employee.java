package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "employees",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    private String role = "STAFF";

    private String skills;

    private Integer maxWeeklyHours;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeAvailability> availabilities;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<GeneratedShiftSchedule> schedules;

    public Employee() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getSkills() { return skills; }
    public Integer getMaxWeeklyHours() { return maxWeeklyHours; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setSkills(String skills) { this.skills = skills; }
    public void setMaxWeeklyHours(Integer maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }
}
