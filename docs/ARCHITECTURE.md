# Architecture and Data Flow

## Overview

SpringBootDemo is a layered Spring Boot REST service:

- `Controller` handles HTTP requests.
- `CustomerService` contains business logic and orchestration.
- `CustomerRepo` (Spring Data JPA) handles persistence.
- `Customer` entity maps to the `customer` table in H2.

## Request Flow

1. Client calls endpoint in `Controller`.
2. Controller invokes `CustomerService`.
3. Service calls `CustomerRepo` (`findAll`, `save`).
4. JPA/Hibernate maps entity objects to SQL operations.
5. Response is serialized to JSON.

## Data Model

Main table: `customer`

Columns from `schema.sql`:

- `id` (auto increment primary key)
- `first_name`
- `last_name`
- `account_number`
- `createdAt`
- `updatedAt`

The application seeds sample rows from `import.sql` at startup.

## Configuration

Key configuration file: `src/main/resources/application.properties`

- `server.port=8080`
- In-memory H2 datasource (`jdbc:h2:mem:dcbapp`)
- SQL init enabled (`spring.sql.init.mode=always`)
- H2 console enabled
- OpenAPI docs path (`/api-docs`)
- Actuator endpoints exposed

## Deployment Artifacts

- `Dockerfile`: multi-stage container build.
- `deployment.yaml`: Kubernetes deployment.
- `javademo-service.yaml`: Kubernetes service.

## Runtime Notes

- Database is in-memory, so data is reset on restart.
- Health checks are available at `/actuator/health`.
- Swagger UI is available for endpoint exploration.
