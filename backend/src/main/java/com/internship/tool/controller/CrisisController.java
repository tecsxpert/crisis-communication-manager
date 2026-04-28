package com.internship.tool.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internship.tool.entity.Crisis;
import com.internship.tool.service.CrisisService;

@RestController
@RequestMapping("/crisis")
@CrossOrigin(origins = "http://localhost:5173")
public class CrisisController {

    private final CrisisService service;

    public CrisisController(CrisisService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Crisis crisis) {
        try {
            Crisis saved = service.create(crisis);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // ✅ GET ALL WITH PAGINATION (Day 5)
    @GetMapping
    public ResponseEntity<Page<Crisis>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(service.getAll(page, size));
    }

    // ✅ GET BY ID (Detail Page)
    @GetMapping("/{id}")
    public ResponseEntity<Crisis> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Crisis> update(@PathVariable Long id, @RequestBody Crisis crisis) {
        return ResponseEntity.ok(service.update(id, crisis));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    // ✅ SEARCH (simple search)
    @GetMapping("/search")
    public ResponseEntity<List<Crisis>> search(@RequestParam String q) {
        return ResponseEntity.ok(service.search(q));
    }

    // ✅ STATS API (Day 6)
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    // ✅ FILTER API (Day 7 - search/filter bar support)
    @GetMapping("/filter")
    public ResponseEntity<Page<Crisis>> filter(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        LocalDateTime startDate = (start != null && !start.isEmpty())
                ? LocalDateTime.parse(start)
                : null;

        LocalDateTime endDate = (end != null && !end.isEmpty())
                ? LocalDateTime.parse(end)
                : null;

        return ResponseEntity.ok(
                service.filter(title, status, startDate, endDate, page, size)
        );
    }

    // 🔥 OPTIONAL (Bonus for Day 8 - AI Panel support)
    @GetMapping("/ai-summary")
    public ResponseEntity<String> getAISummary() {
        return ResponseEntity.ok(
                "AI Summary: Crisis is being monitored. Take immediate action if priority is high."
        );
    }
}