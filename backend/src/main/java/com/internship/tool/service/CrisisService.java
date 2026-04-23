package com.internship.tool.service;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.tool.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CrisisService {

    @Autowired
    private CrisisRepository crisisRepository;

    public Crisis createCrisis(Crisis crisis) {
        if (crisis.getTitle() == null || crisis.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return crisisRepository.save(crisis);
    }

    public List<Crisis> getAllCrisis() {
        return crisisRepository.findAll();
    }

    public Crisis getCrisisById(Long id) {
        return crisisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crisis not found with id: " + id));
    }
}