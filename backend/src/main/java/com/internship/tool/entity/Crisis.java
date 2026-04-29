package com.internship.tool.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Crisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String severity;
}