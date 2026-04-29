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
        return service.create(crisis);
    }

    @GetMapping
    public List<Crisis> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Crisis getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Crisis update(@PathVariable Long id, @RequestBody Crisis crisis) {
        return service.update(id, crisis);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}