package com.internship.tool.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

import com.internship.tool.entity.Crisis;
import com.internship.tool.repository.CrisisRepository;

@Service
public class CrisisService {

    @Autowired
    private CrisisRepository repository;

    @Autowired
    private AiServiceClient aiServiceClient;

    public Crisis createCrisis(Crisis crisis) {
        Crisis saved = repository.save(crisis);
        generateAi(saved);
        return saved;
    }

    @Async
    public void generateAi(Crisis crisis) {
        Map<String, Object> aiResponse =
                aiServiceClient.callDescribe(crisis.getDescription());

        if (aiResponse != null) {
            crisis.setSeverity((String) aiResponse.get("severity"));
            crisis.setSummary((String) aiResponse.get("summary"));
            repository.save(crisis);
        }
    }

    // Get all crisis
    public List<Crisis> getAllCrisis() {
        return repository.findAll();
    }

    // Get crisis by ID
    public Crisis getCrisisById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Search (simple version)
    public List<Crisis> searchCrisis(String severity, String keyword) {
        return repository.findAll();
    }
}