package com.internship.tool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;

@Service
public class CrisisService {

    private final CrisisRepository repository;

    public CrisisService(CrisisRepository repository) {
        this.repository = repository;
    }

    public Crisis create(Crisis crisis) {
        return repository.save(crisis);
    }

    public List<Crisis> getAll() {
        return repository.findAll();
    }

    public Crisis getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Crisis update(Long id, Crisis crisis) {
        Crisis existing = getById(id);
        existing.setTitle(crisis.getTitle());
        existing.setStatus(crisis.getStatus());
        existing.setPriority(crisis.getPriority());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Crisis> search(String q) {
        return repository.findByTitleContainingIgnoreCase(q);
    }
}