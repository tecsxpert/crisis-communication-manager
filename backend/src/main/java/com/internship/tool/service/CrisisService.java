package com.internship.tool.service;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrisisService {

    @Autowired
    private CrisisRepository repo;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CrisisService.class);

    public Crisis create(Crisis crisis) {
        log.info("Creating crisis: {}", crisis.getTitle());
        return repo.save(crisis);
    }

    public List<Crisis> getAll() {
        log.info("Fetching all crises");
        return repo.findAll();
    }

    public Crisis getById(Long id) {
        log.info("Fetching crisis with ID: {}", id);
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Crisis update(Long id, Crisis crisis) {
        log.info("Updating crisis ID: {}", id);
        Crisis existing = getById(id);
        existing.setTitle(crisis.getTitle());
        existing.setDescription(crisis.getDescription());
        existing.setSeverity(crisis.getSeverity());
        return repo.save(existing);
    }

    public void delete(Long id) {
        log.info("Deleting crisis ID: {}", id);
        repo.deleteById(id);
    }
}