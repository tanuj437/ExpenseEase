# ExpenseEase — Spring Boot Expense Tracker

ExpenseEase is a small **Java 17 / Spring Boot** application for tracking daily expenses.
It exposes REST APIs for expenses and categories, and includes a monthly summary endpoint for quick reporting.

The goal was to practice clean domain modeling (entities, services, repositories) and keep the setup simple so it runs out of the box.

## How to run

**Prerequisites**
- Java 17
- Maven 3.9+

**Steps**
```bash
git clone https://github.com/your-username/ExpenseEase.git
cd ExpenseEase
mvn spring-boot:run
```

The app starts on `http://localhost:8080`. An in-file H2 database is used by default so you don’t have to install anything. Sample data is preloaded via `data.sql`.

H2 console (optional): `http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:file:./.h2/expenseease`

## API overview

### Expenses
- `GET /api/expenses` — list all
- `GET /api/expenses/{id}` — get one
- `GET /api/expenses/range?start=YYYY-MM-DD&end=YYYY-MM-DD` — filter by date
- `GET /api/expenses/summary?year=2025` — monthly totals for a year
- `POST /api/expenses` — create (body: `{ description, amount, date, paymentMethod, category, notes }`)
- `PUT /api/expenses/{id}` — update
- `DELETE /api/expenses/{id}` — remove

### Categories
- `GET /api/categories` — list all
- `GET /api/categories/{id}` — get one
- `POST /api/categories` — create (body: `{ name }`)

## Switching to MySQL (optional)

If you prefer MySQL:

1. Add the dependency in `pom.xml`:
```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>
```

2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expenseease
spring.datasource.username=root
spring.datasource.password=yourpass
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=false
```

3. Create the database manually:
```sql
CREATE DATABASE expenseease;
```

## Notes
- No Lombok to keep the code plain Java.
- Controller → Service → Repository layering for clarity.
- The monthly summary uses a simple JPQL aggregation query.
- If you’re pairing this with a frontend, enable CORS in `application.properties`.

---

**Author:** Tanuj Saxena  
I built this to sharpen my Java + Spring Boot fundamentals and keep a small, focused codebase I can iterate on.
