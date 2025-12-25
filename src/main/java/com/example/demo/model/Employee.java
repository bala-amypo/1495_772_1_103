package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    private String email;

    private String role = "STAFF";

    private String skills;

    private Integer maxWeeklyHours;

    private LocalDateTime createdAt;

    public Employee() {}

    public Employee(String fullName, String email, String role, String skills, Integer maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role != null ? role : "STAFF";
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (role == null) role = "STAFF";
    }

    public Set<String> getSkills() {
        if (skills == null) return new HashSet<>();
        return new HashSet<>(Arrays.asList(skills.split(",")));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) {
        this.role = role != null ? role : "STAFF";
    }

    public String getSkillsRaw() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public Integer getMaxWeeklyHours() { return maxWeeklyHours; }
    public void setMaxWeeklyHours(Integer maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
