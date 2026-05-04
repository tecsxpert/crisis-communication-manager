package com.internship.tool.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class AiServiceClient {

    private final String BASE_URL = "http://localhost:5000";

    public Map<String, Object> callDescribe(String input) {

        RestTemplate restTemplate = new RestTemplate();

        String url = BASE_URL + "/describe";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = Map.of("input", input);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, request, Map.class);

        return response.getBody();
    }
}