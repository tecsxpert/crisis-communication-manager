package com.internship.tool.controller;

import com.internship.tool.entity.Crisis;
import com.internship.tool.service.CrisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crisis")
public class CrisisController {

    @Autowired
    private CrisisService crisisService; // 🔥 THIS WAS MISSING

    // CREATE
    @PostMapping
    public Crisis create(@RequestBody Crisis crisis) {
        return crisisService.createCrisis(crisis);
    }

    // GET ALL
    @GetMapping
    public List<Crisis> getAll() {
        return crisisService.getAllCrisis();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Crisis getById(@PathVariable Long id) {
        return crisisService.getCrisisById(id);
    }

    // 🔍 SEARCH (Day 7)
    @GetMapping("/search")
    public List<Crisis> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String severity) {

        return crisisService.searchCrisis(title, severity);
    }
}