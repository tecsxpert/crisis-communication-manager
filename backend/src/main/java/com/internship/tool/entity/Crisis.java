package com.internship.tool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@NotBlank
private String title;

@NotBlank private String description;

@Entity public class Crisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String severity;

    // ✅ DEFAULT CONSTRUCTOR (REQUIRED)
    public Crisis() {
    }

    // ✅ PARAMETERIZED CONSTRUCTOR (REQUIRED FOR TESTS)
    public Crisis(String title, String description, String severity, String dummy) {
        this.title = title;
        this.description = description;
        this.severity = severity;
    }

    // ✅ GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}