package com.internship.tool.repository;

import com.internship.tool.entity.Crisis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrisisRepository extends JpaRepository<Crisis, Long> {
}