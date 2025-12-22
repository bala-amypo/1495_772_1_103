package com.example.demo.entity;

import java.time.LocalTime;
import jakarta.persistence.*;

@Entity
@Table(
    name = "shift_templates",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"templateName", "department_id"}
    )
)
public class ShiftTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    private LocalTime startTime;
    private LocalTime endTime;

    private String requiredSkills;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }
}
