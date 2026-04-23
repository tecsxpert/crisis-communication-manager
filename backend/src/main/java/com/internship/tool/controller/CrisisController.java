package com.internship.tool.controller;

import com.internship.tool.entity.Crisis;
import com.internship.tool.service.CrisisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crisis")
public class CrisisController {

    @Autowired
    private CrisisService service;

    // ✅ POST - Create Crisis
    @PostMapping
    public ResponseEntity<Crisis> create(@Valid @RequestBody Crisis crisis) {
        return ResponseEntity.ok(service.createCrisis(crisis));
    }

    // ✅ GET - All Crisis
    @GetMapping
    public ResponseEntity<List<Crisis>> getAll() {
        return ResponseEntity.ok(service.getAllCrisis());
    }

    // ✅ GET - By ID
    @GetMapping("/{id}")
    public ResponseEntity<Crisis> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCrisisById(id));
    }
}