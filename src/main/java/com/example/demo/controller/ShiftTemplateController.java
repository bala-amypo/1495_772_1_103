package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "shift_templates")
public class ShiftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String requiredSkills;

    @ManyToOne
    private Department department;

    public ShiftTemplate() {}

    public ShiftTemplate(String templateName,
                         LocalTime startTime,
                         LocalTime endTime,
                         String requiredSkills,
                         Department department) {
        this.templateName = templateName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredSkills = requiredSkills;
        this.department = department;
    }

    // ✅ REQUIRED
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getTemplateName() { return templateName; }
    public Department getDepartment() { return department; }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
