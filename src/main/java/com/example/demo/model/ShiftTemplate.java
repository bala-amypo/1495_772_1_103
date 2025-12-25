package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shift_templates",
       uniqueConstraints = @UniqueConstraint(columnNames = {"templateName", "department_id"}))
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String templateName;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private String requiredSkills;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public ShiftTemplate() {}

    public ShiftTemplate(String templateName, LocalTime startTime, LocalTime endTime,
                         String requiredSkills, Department department) {
        this.templateName = templateName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredSkills = requiredSkills;
        this.department = department;
    }

    public Set<String> getRequiredSkills() {
        if (requiredSkills == null) return new HashSet<>();
        return new HashSet<>(Arrays.asList(requiredSkills.split(",")));
    }

    public Long getId() { return id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getRequiredSkillsRaw() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
