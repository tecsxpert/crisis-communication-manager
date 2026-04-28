package com.internship.tool.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // ✅ GET ALL WITH PAGINATION
    @GetMapping
    public ResponseEntity<Page<Crisis>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(service.getAll(page, size));
    }

    // ✅ GET BY ID
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

    // ✅ SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<Crisis>> search(@RequestParam String q) {
        return ResponseEntity.ok(service.search(q));
    }

    // ✅ STATS
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    // ✅ FILTER
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

    // ✅ AI SUMMARY
    @GetMapping("/ai-summary")
    public ResponseEntity<String> getAISummary() {
        return ResponseEntity.ok(
                "AI Summary: Crisis is being monitored. Take immediate action if priority is high."
        );
    }

    // 🔥 CSV EXPORT
    @GetMapping("/export")
    public ResponseEntity<String> exportCSV() {
        try {
            List<Crisis> list = service.getAll();

            StringBuilder csv = new StringBuilder();
            csv.append("ID,Title,Status,Priority\n");

            for (Crisis c : list) {
                csv.append(c.getId() != null ? c.getId() : "").append(",")
                   .append(c.getTitle() != null ? c.getTitle() : "").append(",")
                   .append(c.getStatus() != null ? c.getStatus() : "").append(",")
                   .append(c.getPriority() != null ? c.getPriority() : "").append("\n");
            }

            return ResponseEntity.ok()
                    .header("Content-Type", "text/csv")
                    .header("Content-Disposition", "attachment; filename=crisis.csv")
                    .body(csv.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Export failed: " + e.getMessage());
        }
    }

    // 🔥 FILE UPLOAD (ONLY ONE METHOD)
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("File too large (max 2MB)");
        }

        if (!file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.badRequest().body("Only CSV files allowed");
        }

        try {
            String content = new String(file.getBytes());
            System.out.println(content);

            return ResponseEntity.ok("File uploaded successfully ✅");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed ❌");
        }
    }
}