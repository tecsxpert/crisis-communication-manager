package com.internship.tool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.internship.tool.entity.Crisis;

@Repository
public interface CrisisRepository extends 
        JpaRepository<Crisis, Long>,
        JpaSpecificationExecutor<Crisis> {   // 🔥 IMPORTANT (for filters)

    // 🔍 SEARCH (optional - you can still keep this)
    List<Crisis> findByTitleContainingIgnoreCase(String title);

    // 📊 STATS (Day 6)
    long countByStatus(String status);

    long countByPriority(String priority);
}