package com.example.demo.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String role;
    private String skills;
    private int maxWeeklyHours;

    public Employee() {}

    public Employee(String fullName, String email, String role, String skills, int maxWeeklyHours) {
        this.fullName = fullName;
        this.email = email;
        this.role = (role == null ? "STAFF" : role);
        this.skills = skills;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    /* ----------------- Helpers ----------------- */
    public List<String> getSkills() {
        if (skills == null) return Collections.emptyList();
        return Arrays.asList(skills.split(","));
    }

    /* ----------------- Getters / Setters ----------------- */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getSkillsRaw() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public int getMaxWeeklyHours() { return maxWeeklyHours; }
    public void setMaxWeeklyHours(int maxWeeklyHours) { this.maxWeeklyHours = maxWeeklyHours; }
}
