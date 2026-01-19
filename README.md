# Secure Software Design API

A Spring Boot REST API demonstrating secure software design principles with token-based authentication and authorization.

## Features

- Token-based authentication
- Role-based access control (RBAC)
- BCrypt password hashing
- HTTPS/SSL support
- Security headers (CSP, HSTS, X-Frame-Options)
- Input validation
- Centralized exception handling

## Prerequisites

- Java 21
- PostgreSQL
- Maven

## Configuration

Create a `.env` file based on `.env.example`:

```
DB_URL=jdbc:postgresql://localhost:5432/your_database
DB_USER=your_username
DB_PASSWORD=your_password
SSL_KEYSTORE_PASSWORD=your_keystore_password
SSL_ENABLED=true
PORT=8443
```

## Build & Run

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run
```

The API runs on `https://localhost:8443` by default.

## API Endpoints

### Public

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Authenticate user |
| GET | `/health` | Health check |

### Protected

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users/me` | Get current user profile |
| POST | `/users/create` | Create new user (Admin only) |
| POST | `/users/logout` | Logout and invalidate tokens |

## Project Structure

```
src/main/java/com/api/main/
├── controllers/     # REST endpoints
├── services/        # Business logic
├── entities/        # JPA entities
├── repositories/    # Data access
├── security/        # Security configuration
├── dto/             # Data transfer objects
└── config/          # App configuration
```
