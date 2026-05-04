# Security

## Overview
This project includes basic security practices to ensure safe and valid data handling.

## Input Validation
- Implemented using annotations such as @NotBlank
- Ensures that required fields like title and description are not empty

## Exception Handling
- Implemented using @ControllerAdvice
- Provides centralized error handling
- Returns meaningful error responses instead of system crashes

## Authentication
- Basic JWT configuration is included / planned
- Used for securing API endpoints (future enhancement)

## Notes
- Security is currently kept simple for development purposes
- Can be extended with full JWT authentication and role-based access control