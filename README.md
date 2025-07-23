# 📊 Accounting App – Spring Boot Application

This is a Spring Boot project for managing **users**, **accounts**, and **transactions** in a simple accounting system.

The project uses:
- **PostgreSQL** database (via Docker)
- **Liquibase** for DB schema and mock data
- **Spring Boot** 3.5.3 (JDK 21)

---

## 🚀 Requirements

- Java **JDK 21**
- **Docker** & **Docker Compose**

---

## 🐳 Database setup

This project includes a `docker-compose.yml` file to run PostgreSQL as a local container.

**Start the database**:
```bash
docker compose up -d
```
---

## 🛠 Running the application
After starting the database, run the Spring Boot app:
```bash
./gradlew bootRun
```
---
## 📦 API Overview

### ✅ AuthController
| Method | Endpoint | Description |
|:--:|--|--|
| POST | `/api/v1/auth/login` | Login and get token |

> 📄 Test this API using: **auth-controller.http**

### 👤 **UserController**
| Method | Endpoint | Description |
|:--:|--|--|
| GET | `/api/v1/user/get-all` | Get all users |
| GET | `/api/v1/user/get/{id}` | Get user by ID |
| POST | `/api/v1/user/create` | Create new user |
| PUT | `/api/v1/user/update/{userId}` | Update user |

> 📄 Test these APIs using: **user-controller.http**

---
## 💸 **TransactionController**
| Method | Endpoint | Description |
|:--:|--|--|
| GET | `/api/v1/transaction/get-all` | Get all transactions |
| GET | `/api/v1/transaction/get-all/{userId}` | Get transactions by user ID |
| GET | `/api/v1/transaction/get-all/by-date?dateFrom=...&dateTo=...` | Get transactions in date range |
| POST | `/api/v1/transaction/transfer` | Transfer money between accounts |

> 📄 Test these APIs using: **transaction-controller.http**

---
## 📄 Liquibase
Liquibase automatically handles database migration:

Creates schema & tables:

user

account

transaction

Inserts mock data:

5 users with unique accounts

10 sample transactions
