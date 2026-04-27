package com.internship.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internship.tool.entity.Crisis;

@Repository
public interface CrisisRepository extends JpaRepository<Crisis, Long> {

    // 🔍 SEARCH
    List<Crisis> findByTitleContainingIgnoreCase(String title);

    // 📊 STATS (Day 6 requirement)
    long countByStatus(String status);

    long countByPriority(String priority);
}