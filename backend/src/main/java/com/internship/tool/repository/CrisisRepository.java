package com.internship.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internship.tool.entity.Crisis;

public interface CrisisRepository extends JpaRepository<Crisis, Long> {
    List<Crisis> findByTitleContainingIgnoreCase(String title);
}