package com.example.demo.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Department {

    private Long id;
    private String name;
    private String description;
    private String requiredSkills;

    public Department() {}

    public Department(String name, String description, String requiredSkills) {
        this.name = name;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    public Set<String> getRequiredSkills() {
        if (requiredSkills == null || requiredSkills.isEmpty()) return new HashSet<>();
        return new HashSet<>(Arrays.asList(requiredSkills.split(",")));
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequiredSkillsRaw() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
}
