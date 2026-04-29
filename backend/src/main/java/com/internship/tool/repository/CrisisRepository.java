package com.internship.tool.repository;

import com.internship.tool.entity.Crisis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrisisRepository extends JpaRepository<Crisis, Long> {

    // 🔍 Search by title
    List<Crisis> findByTitleContainingIgnoreCase(String title);

    // 🔍 Filter by severity
    List<Crisis> findBySeverity(String severity);

    // 🔍 Search + filter combined
    List<Crisis> findByTitleContainingIgnoreCaseAndSeverity(String title, String severity);
}