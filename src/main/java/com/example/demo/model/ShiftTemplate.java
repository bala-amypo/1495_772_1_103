package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(
    name = "shift_templates",
    uniqueConstraints = @UniqueConstraint(columnNames = {"templateName", "department_id"})
)
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
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "shiftTemplate", fetch = FetchType.LAZY)
    private List<GeneratedShiftSchedule> schedules;

    public ShiftTemplate() {}

    public Long getId() { return id; }
    public String getTemplateName() { return templateName; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public String getRequiredSkills() { return requiredSkills; }
    public Department getDepartment() { return department; }

    public void setId(Long id) { this.id = id; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
    public void setDepartment(Department department) { this.department = department; }
}
