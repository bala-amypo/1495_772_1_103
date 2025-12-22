package com.example.employee.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(
    name = "shift_template",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"templateName", "department_id"})
    }
)
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String templateName;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    // comma-separated skills
    private String requiredSkills;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // getters & setters
    public Long getId() { return id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
