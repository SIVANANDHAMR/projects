# Employee Management & Payroll (Java + Oracle)

Minimal scaffold for a demo project you can show on your resume.

Files included:
- Spring Boot app (Java 17)
- JPA entities for Employee and Department
- REST controller for basic Employee CRUD
- Oracle schema SQL and a PL/SQL payroll procedure

How to run locally
1. Start Oracle XE (example using gvenzl image):
   docker run -d --name oracle-xe -p 1521:1521 -e ORACLE_PASSWORD=YourPass123 gvenzl/oracle-xe:18.4.0

2. Connect as SYSTEM and run src/main/resources/db/schema.sql to create the hr_app user and tables. For example:
   docker exec -it oracle-xe bash
   sqlplus system/YourPass123@//localhost:1521/XE
   @schema.sql
   @run_payroll_proc.sql

3. Update src/main/resources/application.properties with the correct username/password if needed.

4. Build and run:
   mvn clean package
   mvn spring-boot:run

