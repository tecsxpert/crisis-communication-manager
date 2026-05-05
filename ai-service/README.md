# Crisis Communication Manager


# AI Service — Crisis Communication Manager

## Overview

The AI Service is a Flask-based microservice responsible for generating intelligent responses using the Groq API (LLaMA model). It provides endpoints for:

* Describing crisis situations
* Recommending actions
* Generating structured reports

The service runs on port **5000** and communicates with the Spring Boot backend.

---

## Tech Stack

* Python 3.11
* Flask 3.x
* Groq API (LLaMA-3.3-70b)
* Redis (for caching)
* flask-limiter (rate limiting)

---

## Folder Structure

```
ai-service/
│-- routes/
│-- services/
│   └── groq_client.py
│-- prompts/
│-- app.py
│-- requirements.txt
│-- Dockerfile
```

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd ai-service
```

### 2. Create Virtual Environment

```bash
python -m venv venv
source venv/bin/activate   # Linux/Mac
venv\Scripts\activate      # Windows
```

### 3. Install Dependencies

```bash
pip install -r requirements.txt
```

### 4. Configure Environment Variables

Create a `.env` file in the root directory:

```
GROQ_API_KEY=your_groq_api_key
REDIS_HOST=localhost
REDIS_PORT=6379
RATE_LIMIT=30/minute
```

---

## Running the Service

### Run Locally

```bash
python app.py
```

Service will start at:

```
http://localhost:5000
```

### Health Check

```
GET /health
```

---

## API Endpoints

### 1. POST /describe

Generates a structured description of a crisis.

#### Request

```json
{
  "input": "Flood in Bangalore affecting 200 families"
}
```

#### Response

```json
{
  "description": "Severe flooding has impacted multiple households...",
  "generated_at": "2026-05-05T12:00:00Z"
}
```

---

### 2. POST /recommend

Provides 3 actionable recommendations.

#### Request

```json
{
  "input": "Flood in Bangalore affecting 200 families"
}
```

#### Response

```json
[
  {
    "action_type": "Emergency Response",
    "description": "Deploy rescue teams...",
    "priority": "High"
  },
  {
    "action_type": "Relief",
    "description": "Provide food and shelter...",
    "priority": "Medium"
  },
  {
    "action_type": "Communication",
    "description": "Issue public alerts...",
    "priority": "High"
  }
]
```

---

### 3. POST /generate-report

Generates a full structured report.

#### Request

```json
{
  "input": "Flood in Bangalore affecting 200 families"
}
```

#### Response

```json
{
  "title": "Flood Crisis Report",
  "summary": "A major flood has impacted...",
  "overview": "Detailed situation analysis...",
  "key_items": ["200 families affected", "Infrastructure damage"],
  "recommendations": ["Deploy rescue teams", "Set up shelters"]
}
```

---

## Error Handling

* Returns **400** for invalid input
* Returns fallback response if Groq API fails:

```json
{
  "is_fallback": true
}
```

---

## Rate Limiting

* Configured using `flask-limiter`
* Default: **30 requests per minute per IP**

---

## Redis Caching

* Responses cached using SHA256 hash of input
* TTL: **15 minutes**

---

## Running with Docker

### Build Image

```bash
docker build -t ai-service .
```

### Run Container

```bash
docker run -p 5000:5000 --env-file .env ai-service
```

---

## Notes

* Ensure Groq API key is valid
* Do not commit `.env` file
* Use fallback responses for reliability

---

## Author

Capstone Project — Crisis Communication Manager
