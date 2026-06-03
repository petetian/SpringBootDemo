# SpringBootDemo

SpringBootDemo is a Java 25 Spring Boot 3.3 REST API sample. It exposes a small customer API backed by an in-memory H2 database, includes OpenAPI/Swagger UI for exploration, and ships with Docker and Kubernetes manifests for container-based deployment.

## Docs

- `docs/README.md` documentation index
- `docs/API.md` endpoint reference
- `docs/ARCHITECTURE.md` service architecture and data flow

## Stack

- Java 25
- Spring Boot 3.3.6
- Spring Web
- Spring Data JPA
- H2 in-memory database
- Springdoc OpenAPI / Swagger UI
- Spring Boot Actuator
- Docker
- Kubernetes manifests

## What the App Does

The application starts an HTTP server on port 8080 by default and initializes an H2 database from SQL scripts during startup.

Available endpoints:

- `GET /` returns `Spring boot is running!`
- `GET /greeting` returns `Hello from Microsoft`
- `GET /customers` returns all seeded and created customers
- `POST /create` creates a customer record
- `GET /actuator/health` returns service health

Developer endpoints:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`
- H2 console: `http://localhost:8080/h2-console`

## Data Model

The main table is `customer` with these fields:

- `id`
- `first_name`
- `last_name`
- `account_number`
- `createdAt`
- `updatedAt`

The app seeds two customers on startup:

- John Doe
- Jane Smith

## Prerequisites

- Java 25
- Maven 3.9+ or the included Maven wrapper
- Docker, if you want to build the container image
- A Kubernetes cluster, if you want to use the provided manifests

## Run Locally

Use the Maven wrapper from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

Or build the jar and run it:

```powershell
.\mvnw.cmd clean package
java -jar .\target\app.jar
```

The API will be available at `http://localhost:8080`.

## Example Requests

Get all customers:

```powershell
Invoke-RestMethod http://localhost:8080/customers
```

Create a customer:

```powershell
$body = @{
  firstName = "Alice"
  lastName = "Walker"
  accountNumber = "A100200"
} | ConvertTo-Json

Invoke-RestMethod -Method Post \
  -Uri http://localhost:8080/create \
  -ContentType 'application/json' \
  -Body $body
```

## Database Notes

The application uses an in-memory H2 database configured in `src/main/resources/application.properties`.

- JDBC URL: `jdbc:h2:mem:dcbapp`
- Username: `sa`
- Password: `DBp@ssword`
- SQL init mode: enabled

Because the database is in memory, data is reset whenever the application restarts.

## Build a Docker Image

The included Dockerfile uses a multi-stage build:

```powershell
docker build -t springbootdemo:local .
docker run --rm -p 8080:8080 springbootdemo:local
```

## Deploy with Kubernetes Manifests

This repository includes:

- `deployment.yaml` for the application deployment
- `javademo-service.yaml` for a `LoadBalancer` service

Apply them with:

```powershell
kubectl apply -f deployment.yaml
kubectl apply -f javademo-service.yaml
```

Notes:

- The deployment references the image `ptdevregistry.azurecr.io/springbootdemo:latest`
- The container exposes port 8080
- The liveness and startup probes use `GET /actuator/health`
- The service maps port 80 to container port 8080

## Project Layout

```text
src/main/java/com/pt/springdemo/
  Controller.java
  CustomerService.java
  DemoApplication.java
  entity/
  repo/

src/main/resources/
  application.properties
  schema.sql
  import.sql
```

## Development Notes

- Maven tests are currently skipped through the project configuration in `pom.xml`
- The application is configured for local testing on port 8080
- In App Service scenarios, the configured port may be overridden by the hosting environment