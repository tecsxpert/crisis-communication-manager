# 🔐 SECURITY POLICY — Crisis Communication Manager

## 📌 Overview

This project implements basic security practices to ensure safe handling of data and API communication. The system is designed for demonstration purposes with attention to common security principles.

---

## 🔑 1. Sensitive Data Protection

- Database credentials are NOT hardcoded.
- Environment variables are used:
  
- `.env` file is used locally and excluded from GitHub.

---

## 🌐 2. API Security

- REST APIs follow proper HTTP status codes.
- Input validation is applied for:
- File uploads (type & size validation)
- Request parameters

- Global Exception Handling implemented using:
- `@RestControllerAdvice`

---

## 📁 3. File Upload Security

- Only CSV files are allowed.
- File size limit enforced (max 2MB).
- Invalid files are rejected with proper error messages.

---

## 🛡️ 4. Database Security

- PostgreSQL used with authentication.
- No direct exposure of database credentials.
- Queries handled via Spring Data JPA (prevents SQL Injection).

---

## 🔍 5. Input Validation & Injection Prevention

- No raw SQL queries used.
- JPA repository methods prevent SQL injection.
- User inputs are validated before processing.

---

## 📊 6. Logging & Error Handling

- Debug logs removed from production code.
- Errors are handled using Global Exception Handler.
- No sensitive data exposed in error messages.

---

## 🚫 7. Known Limitations

- No authentication/authorization (JWT) implemented (future scope).
- No rate limiting applied.
- No HTTPS enforced (development environment).

---

## 🚀 8. Future Improvements

- Add JWT-based authentication
- Add role-based access control (RBAC)
- Implement rate limiting
- Enable HTTPS in production
- Integrate security scanning tools

---

## 📞 Contact

For security issues, please contact the project maintainer.

---

## ✅ Status

✔ Security practices applied  
✔ No hardcoded secrets  
✔ Safe for demo and evaluation  