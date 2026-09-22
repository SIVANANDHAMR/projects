# Employee Management & Payroll

A Spring Boot 3 REST API for employee records backed by Oracle Database. The project demonstrates JPA entity modeling, validation, CRUD endpoints, environment-based configuration, and a PL/SQL payroll procedure.

## Highlights

- Java 17 and Spring Boot 3
- Employee CRUD API at `/api/employees`
- Request validation for names, email, dates, and salary
- Oracle schema, reporting view, and monthly payroll procedure
- MVC controller tests that run without an Oracle instance

## Run locally

1. Start Oracle XE:

   ```bash
   docker run -d --name oracle-xe -p 1521:1521 \
     -e ORACLE_PASSWORD=YourPass123 gvenzl/oracle-xe:18.4.0
   ```

2. Copy the SQL files into the container and run them as `SYSTEM`. The schema script creates the `hr_app` user and switches to that schema before creating tables:

   ```bash
   docker cp src/main/resources/db/schema.sql oracle-xe:/tmp/schema.sql
   docker cp src/main/resources/db/run_payroll_proc.sql oracle-xe:/tmp/run_payroll_proc.sql
   docker exec -it oracle-xe sqlplus system/YourPass123@//localhost:1521/XE
   @/tmp/schema.sql
   @/tmp/run_payroll_proc.sql
   ```

3. Start the API:

   ```bash
   mvn spring-boot:run
   ```

   The default connection values are `hr_app` / `hr_password`. Override them without changing source files:

   ```bash
   DB_USERNAME=hr_app DB_PASSWORD=hr_password mvn spring-boot:run
   ```

## API examples

Create an employee:

```bash
curl -X POST http://localhost:8080/api/employees \
  -H 'Content-Type: application/json' \
  -d '{"firstName":"Ada","lastName":"Lovelace","email":"ada@example.com","salary":120000}'
```

List employees:

```bash
curl http://localhost:8080/api/employees
```

## Test

```bash
mvn test
```
