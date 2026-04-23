package com.internship.tool.service;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrisisService {

    private final CrisisRepository repository;

    public CrisisService(CrisisRepository repository) {
        this.repository = repository;
    }

    public Crisis saveCrisis(Crisis crisis) {
        return repository.save(crisis);
    }

    public List<Crisis> getAll() {
        return repository.findAll();
    }
}