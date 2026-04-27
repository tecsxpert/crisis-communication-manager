package com.internship.tool.controller;

import java.util.List;

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
@CrossOrigin(origins = "http://localhost:5173") // allow frontend
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
            e.printStackTrace(); // 🔥 useful for debugging
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
        Crisis updated = service.update(id, crisis);
        return ResponseEntity.ok(updated);
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
}