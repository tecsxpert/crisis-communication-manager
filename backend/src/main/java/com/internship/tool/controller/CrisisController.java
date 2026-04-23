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
    private CrisisService service;

    @PostMapping
    public Crisis create(@RequestBody Crisis crisis) {
        return service.createCrisis(crisis); // ✅ FIXED
    }

    @GetMapping
    public List<Crisis> getAll() {
        return service.getAllCrisis(); // ✅ FIXED
    }

    @GetMapping("/{id}")
    public Crisis getById(@PathVariable Long id) {
        return service.getCrisisById(id);
    }
}