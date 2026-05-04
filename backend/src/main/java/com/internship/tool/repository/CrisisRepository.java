package com.internship.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.internship.tool.entity.Crisis;

@Repository
public interface CrisisRepository extends 
        JpaRepository<Crisis, Long>,
        JpaSpecificationExecutor<Crisis> {   // 🔥 Required for dynamic filtering (Day 7+)

    // 🔍 SEARCH (used in /search API)
    List<Crisis> findByTitleContainingIgnoreCase(String title);

    // 📊 STATS (used in /stats API)
    long countByStatus(String status);

    long countByPriority(String priority);

    // 🔥 OPTIONAL (Day 11 performance improvement)
    // Fetch by status (helps index usage)
    List<Crisis> findByStatus(String status);

    // 🔥 OPTIONAL (filter optimization)
    List<Crisis> findByPriority(String priority);
}