# NitHigh Bites — Nightmare Test Cases

## New Test Files Added

```text
backend/src/test/java/com/nithigh/bites/FoodNightmareValidationTest.java
backend/src/test/java/com/nithigh/bites/FoodNightmareApiTest.java
backend/src/test/java/com/nithigh/bites/FoodNightmareBrokenTest.java
```

## What Makes This Harder

Participants must fix:

- Compile errors
- Missing JPA constructors
- Wrong Spring bean wiring
- Repository query method typo
- Wrong API endpoints
- Wrong request parameters
- Bad validation logic
- Wrong JSON field names
- Failing unit tests
- Failing integration tests
- Intentionally broken test expectations
- Frontend field mismatch
- Cart/order API mismatch
- H2 data initialization issue

## Nightmare API Tests

- Health exact response
- Seed food menu must load
- Search must be case-insensitive
- Unknown search must return empty list
- Category filter must work
- Wrong JSON field should fail
- Order placement should persist
- Invalid food/order must return 400

## Scoring

| Area | Marks |
|---|---:|
| Backend compiles | 15 |
| Spring Boot starts | 10 |
| Food APIs work | 15 |
| Order APIs work | 15 |
| Validation fixed | 15 |
| Frontend fully works | 15 |
| All JUnit tests pass | 10 |
| Code explanation | 5 |
| Total | 100 |

## Final Success Commands

```bash
cd backend
mvn clean test
mvn spring-boot:run
```