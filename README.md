# Crisis Communication Manager
# Crisis Communication Manager

## Overview
A backend system to manage crisis data (create, update, delete, list) with Spring Boot.

## Tech Stack
- Java 17
- Spring Boot
- Spring Security (basic config)
- JPA (Hibernate)
- H2 / PostgreSQL
- Swagger (OpenAPI)

## Features
- Create Crisis
- View All Crises
- Update Crisis
- Delete Crisis
- Data Seeder (auto inserts 30 records)
- Validation & Global Exception Handling
- Logging

## API Endpoints

### GET all crises
GET /api/crisis

### GET by ID
GET /api/crisis/{id}

### CREATE
POST /api/crisis
{
  "title": "Flood",
  "description": "Heavy rain",
  "severity": "HIGH"
}

### UPDATE
PUT /api/crisis/{id}

### DELETE
DELETE /api/crisis/{id}

## Run Locally

```bash
.\mvnw spring-boot:run

#Swagger:
http://localhost:8080/swagger-ui/index.html

Notes
* DataSeeder inserts 30 records on first run
* Security is relaxed for development

---

# 2) 🧪 ADD 2 DEMO ENDPOINTS (HELPS INTERVIEW)

👉 In `CrisisController.java`, add:

```java id="demo1"
@GetMapping("/count")
public long count() {
    return service.getAll().size();
}
@GetMapping("/high")
public List<Crisis> getHighSeverity() {
    return service.getAll().stream()
            .filter(c -> "HIGH".equalsIgnoreCase(c.getSeverity()))
            .toList();
}