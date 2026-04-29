package com.internship.tool.service;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;
import com.internship.tool.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Service
public class CrisisService {

    @Autowired
    private CrisisRepository crisisRepository;

    // 🔐 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "crisisList", allEntries = true)
    public Crisis createCrisis(Crisis crisis) {

        if (crisis.getTitle() == null || crisis.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (crisis.getSeverity() == null || crisis.getSeverity().trim().isEmpty()) {
            throw new IllegalArgumentException("Severity cannot be empty");
        }

        return crisisRepository.save(crisis);
    }

    // 🔐 USER + ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Cacheable("crisisList")
    public List<Crisis> getAllCrisis() {
        System.out.println("Fetching from DB...");
        return crisisRepository.findAll();
    }

    // 🔐 USER + ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Cacheable(value = "crisis", key = "#id")
    public Crisis getCrisisById(Long id) {

        return crisisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crisis not found with id: " + id));
    }

    // 🔍 SEARCH + FILTER
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Crisis> searchCrisis(String title, String severity) {

        if (title != null && severity != null) {
            return crisisRepository
                    .findByTitleContainingIgnoreCaseAndSeverity(title, severity);
        }

        if (title != null) {
            return crisisRepository.findByTitleContainingIgnoreCase(title);
        }

        if (severity != null) {
            return crisisRepository.findBySeverity(severity);
        }

        return crisisRepository.findAll();
    }
}