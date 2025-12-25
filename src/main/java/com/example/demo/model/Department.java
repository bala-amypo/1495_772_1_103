package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    private String description;

    private String requiredSkills;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<ShiftTemplate> shiftTemplates;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<GeneratedShiftSchedule> shiftSchedules;

    public Department() {}

    public Department(String name, String description, String requiredSkills) {
        this.name = name;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<ShiftTemplate> getShiftTemplates() { return shiftTemplates; }
    public void setShiftTemplates(List<ShiftTemplate> shiftTemplates) { this.shiftTemplates = shiftTemplates; }

    public List<GeneratedShiftSchedule> getShiftSchedules() { return shiftSchedules; }
    public void setShiftSchedules(List<GeneratedShiftSchedule> shiftSchedules) { this.shiftSchedules = shiftSchedules; }
}