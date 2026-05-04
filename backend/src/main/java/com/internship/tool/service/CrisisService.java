package com.internship.tool.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.internship.tool.aop.AuditLog;
import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;

@Service
public class CrisisService {

    private final CrisisRepository repository;

    public CrisisService(CrisisRepository repository) {
        this.repository = repository;
    }

    // ✅ CREATE
    @AuditLog("CREATE crisis")
    public Crisis create(Crisis crisis) {
        return repository.save(crisis);
    }

    // ✅ GET ALL (avoid using in large data)
    public List<Crisis> getAll() {
        return repository.findAll();
    }

    // ✅ GET ALL WITH PAGINATION
    public Page<Crisis> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );
        return repository.findAll(pageable);
    }

    // ✅ GET BY ID (🔥 FIXED EXCEPTION)
    public Crisis getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crisis not found with id: " + id));
    }

    // ✅ UPDATE
    @AuditLog("UPDATE crisis")
    public Crisis update(Long id, Crisis crisis) {
        Crisis existing = getById(id);

        existing.setTitle(crisis.getTitle());
        existing.setDescription(crisis.getDescription());
        existing.setStatus(crisis.getStatus());
        existing.setPriority(crisis.getPriority());

        return repository.save(existing);
    }

    // ✅ DELETE
    @AuditLog("DELETE crisis")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ SEARCH
    public List<Crisis> search(String q) {
        return repository.findByTitleContainingIgnoreCase(q);
    }

    // ✅ STATS
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("total", repository.count());
        stats.put("open", repository.countByStatus("ongoing"));
        stats.put("closed", repository.countByStatus("closed"));
        stats.put("highPriority", repository.countByPriority("1st"));

        return stats;
    }

    // ✅ FILTER (Day 7 + Day 11 optimized)
    public Page<Crisis> filter(
            String title,
            String status,
            LocalDateTime start,
            LocalDateTime end,
            int page,
            int size) {

        Specification<Crisis> spec = (root, query, cb) -> cb.conjunction();

        // 🔍 Title filter
        if (title != null && !title.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }

        // 📌 Status filter
        if (status != null && !status.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), status));
        }

        // 📅 Date filter
        if (start != null && end != null) {
            spec = spec.and((root, query, cb) ->
                    cb.between(root.get("createdAt"), start, end));
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return repository.findAll(spec, pageable);
    }
}