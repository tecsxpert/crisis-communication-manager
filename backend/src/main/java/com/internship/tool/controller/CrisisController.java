package com.internship.tool.controller;

import com.internship.tool.entity.Crisis;
import com.internship.tool.service.CrisisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crisis")
public class CrisisController {

    private final CrisisService service;

    public CrisisController(CrisisService service) {
        this.service = service;
    }

    // TEST API
    @GetMapping("/test")
    public String test() {
        return "Backend is working 🚀";
    }

    // CREATE DATA
    @PostMapping
    public Crisis create(@RequestBody Crisis crisis) {
        return service.saveCrisis(crisis);
    }

    // GET ALL DATA
    @GetMapping
    public List<Crisis> getAll() {
        return service.getAll();
    }
}