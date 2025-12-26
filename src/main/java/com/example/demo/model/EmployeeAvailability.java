package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String skills;

    @Column(nullable = false)
    private int maxWeeklyHours;

    public Employee() {}

    public Employee(String fullName, String email, String role,
                    String skills, int maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = role != null ? role : "STAFF";
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    /** BUSINESS LOGIC (NOT STORED IN DB) */
    @Transient
    public Set<String> getSkills() {
        if (skills == null || skills.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(skills.split(",")));
    }

    // ===== Getters & Setters =====

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

    public int getMaxWeeklyHours() { return maxWeeklyHours; }
    public void setMaxWeeklyHours(int maxWeeklyHours) {
        this.maxWeeklyHours = maxWeeklyHours;
    }
}
