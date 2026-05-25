# Secure Vault API

Secure Spring Boot REST API demonstrating modern backend security practices.

## Features

- JWT Authentication
- Role-Based Access Control (ADMIN / USER)
- BCrypt Password Hashing
- AES Email Encryption
- Audit Logging
- Global Exception Handling
- Request Validation
- Swagger/OpenAPI Documentation
- PostgreSQL Database
- Docker & Docker Compose

## Technology Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Hibernate
- JWT
- Swagger/OpenAPI
- Docker

## Running Locally

```bash
mvn spring-boot:run
```

## Running with Docker

```bash
docker compose up -d
```

Swagger UI:

http://localhost:8080/swagger-ui/index.html

## Authentication

Generate JWT:

POST /auth/login

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Use returned token in Authorization header:

```text
Bearer <token>
```
