# Restful Booker API Test Framework

A REST API test automation framework built with Java and REST Assured to validate the [Restful Booker API](https://restful-booker.herokuapp.com). The framework covers full CRUD operations with token-based authentication, POJO-based serialization/deserialization, JSON data-driven testing, JSON schema validation, and Allure reporting.

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java 17 | Programming language |
| REST Assured 5.4.0 | API testing library |
| TestNG 7.10.2 | Test execution framework |
| Jackson | Serialization / Deserialization |
| Allure 2.24.0 | Test reporting |
| Maven | Build and dependency management |

---

## Framework Structure

```
Booker_API_Framework/
│
├── src/test/java/
│   ├── api/
│   │   ├── AuthAPI.java                  # Token generation endpoint
│   │   └── BookingAPI.java               # CRUD operations (GET, POST, PUT, DELETE)
│   │
│   ├── base/
│   │   └── BaseTest.java                 # RequestSpecification setup, shared state (bookingid, token)
│   │
│   ├── models/
│   │   ├── common/
│   │   │   └── BookingDates.java         # Shared POJO used by both request and response
│   │   ├── request/
│   │   │   ├── AuthRequest.java          # Auth token request POJO
│   │   │   ├── CreateBookingRequest.java
│   │   │   └── UpdateBookingRequest.java
│   │   └── response/
│   │       ├── AuthResponse.java         # Token response POJO
│   │       ├── BookingDetailsResponse.java
│   │       └── CreateBookingResponse.java
│   │
│   ├── tests/
│   │   ├── AuthTest.java                 # Token generation test
│   │   └── BookingTest.java              # CRUD test scenarios (positive + negative)
│   │
│   └── utils/
│       ├── ConfigReader.java             # Reads base URL from config.properties
│       ├── JSONReader.java               # Reads test data from JSON files
│       ├── SchemaValidator.java          # JSON schema validation helper
│       └── TokenManager.java            # Manages auth token across test classes
│
├── src/test/resources/
│   ├── config/
│   │   └── config.properties             # Base URL configuration
│   ├── schemas/
│   │   ├── authSchema.json               # JSON schema for auth response
│   │   ├── createBookingSchema.json      # JSON schema for create booking response
│   │   └── updateBookingSchema.json      # JSON schema for update booking response
│   └── testdata/
│       └── bookingData.json              # Data-driven test inputs
│
├── testng.xml                            # Test suite configuration
└── pom.xml
```

---

## Test Scenarios

### AuthTest
| Test | Description |
|---|---|
| `createToken` | Generates auth token using admin credentials |

### BookingTest
| Test | Type | Description |
|---|---|---|
| `createBookingPositive` | Positive | Creates a new booking, validates schema and response fields |
| `getBookingPositive` | Positive | Retrieves booking by valid ID |
| `getBookingNegative` | Negative | Attempts to retrieve booking with invalid ID, expects 404 |
| `updateBooking` | Positive | Updates booking using token auth, verifies name change |
| `deleteBookingPositive` | Positive | Deletes booking using token auth |
| `deleteBookingNegative` | Negative | Attempts to delete non-existent booking |

---

## Key Features

- **POJO Serialization/Deserialization** — Request and response bodies mapped via Jackson POJOs, split across `request`, `response`, and `common` packages
- **Token-Based Authentication** — Auth token generated in `AuthTest`, managed by `TokenManager`, and shared via `BaseTest` static field for use in update and delete operations
- **Data-Driven Testing** — Test inputs stored in `bookingData.json` and read via `JSONReader` utility
- **JSON Schema Validation** — Response structure validated against schema files (`authSchema.json`, `createBookingSchema.json`, `updateBookingSchema.json`) using REST Assured's `JsonSchemaValidator`
- **Request Chaining** — `bookingid` created in `createBooking` flows into GET, PUT, and DELETE tests
- **Allure Reporting** — Tests annotated with `@Description` for rich HTML reports
- **Config-Driven Base URL** — Base URL externalized to `config.properties` via `ConfigReader`

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Run all tests
```bash
mvn clean test
```

### Generate and open Allure report
```bash
mvn allure:serve
```

### Run via TestNG XML directly (Eclipse)
Right-click `testng.xml` → Run As → TestNG Suite

---

## Test Execution Order

Tests are executed in the following order via `testng.xml`:

```
AuthTest       →  createToken()
BookingTest    →  createBookingPositive()
               →  getBookingPositive()
               →  getBookingNegative()
               →  updateBooking()
               →  deleteBookingPositive()
               →  deleteBookingNegative()
```

`AuthTest` must run before `BookingTest` so the token is available for authenticated operations.

---

## API Under Test

**Base URL:** `https://restful-booker.herokuapp.com`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth` | Generate auth token |
| POST | `/booking` | Create booking |
| GET | `/booking/{id}` | Get booking by ID |
| PUT | `/booking/{id}` | Update booking (requires token) |
| DELETE | `/booking/{id}` | Delete booking (requires token) |

---

## Author

Sathya Ravichandran  
QA Automation Engineer  
[GitHub](https://github.com/sathyaravi) • [LinkedIn](https://linkedin.com/in/sathyakk)
