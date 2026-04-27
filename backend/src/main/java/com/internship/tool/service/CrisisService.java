package com.internship.tool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // ✅ GET ALL (optional)
    public List<Crisis> getAll() {
        return repository.findAll();
    }

    // ✅ GET ALL WITH PAGINATION (Day 5)
    public Page<Crisis> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    // ✅ GET BY ID
    public Crisis getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crisis not found with id: " + id));
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

    // 🔥 DAY 6: STATS (VERY IMPORTANT)
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("total", repository.count());
        stats.put("open", repository.countByStatus("ongoing"));
        stats.put("closed", repository.countByStatus("closed"));
        stats.put("highPriority", repository.countByPriority("1st"));

        return stats;
    }
}