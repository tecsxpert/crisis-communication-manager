package com.internship.tool.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users") // 🔥 FIX HERE
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;
}