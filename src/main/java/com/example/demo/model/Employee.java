package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    private String role = "STAFF";

    private String skills;

    @Min(1)
    private Integer maxWeeklyHours;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeAvailability> availabilities;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<GeneratedShiftSchedule> shiftSchedules;

    public Employee() {}

    public Employee(String fullName, String email, String role, String skills, Integer maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public Integer getMaxWeeklyHours() { return maxWeeklyHours; }
    public void setMaxWeeklyHours(Integer maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<EmployeeAvailability> getAvailabilities() { return availabilities; }
    public void setAvailabilities(List<EmployeeAvailability> availabilities) { this.availabilities = availabilities; }

    public List<GeneratedShiftSchedule> getShiftSchedules() { return shiftSchedules; }
    public void setShiftSchedules(List<GeneratedShiftSchedule> shiftSchedules) { this.shiftSchedules = shiftSchedules; }
}