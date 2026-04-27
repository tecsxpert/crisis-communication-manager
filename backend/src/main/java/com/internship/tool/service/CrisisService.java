package com.internship.tool.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;

@Service
public class CrisisService {

    private final CrisisRepository repository;

    public CrisisService(CrisisRepository repository) {
        this.repository = repository;
    }

    // ✅ CREATE
    public Crisis create(Crisis crisis) {
        return repository.save(crisis);
    }

    // ✅ GET ALL (OLD - keep if needed)
    public List<Crisis> getAll() {
        return repository.findAll();
    }

    // ✅ GET ALL WITH PAGINATION (🔥 IMPORTANT FOR DAY 5)
    public Page<Crisis> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    // ✅ GET BY ID
    public Crisis getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    // ✅ UPDATE
    public Crisis update(Long id, Crisis crisis) {
        Crisis existing = getById(id);
        existing.setTitle(crisis.getTitle());
        existing.setStatus(crisis.getStatus());
        existing.setPriority(crisis.getPriority());
        return repository.save(existing);
    }

    // ✅ DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ SEARCH
    public List<Crisis> search(String q) {
        return repository.findByTitleContainingIgnoreCase(q);
    }
}