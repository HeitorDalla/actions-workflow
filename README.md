# Library-Ops
A robust RESTful API for a library management system, built with Java and Spring Boot. This project provides a complete backend solution for managing users, books, loans, reservations, and fines. It is containerized with Docker and features a comprehensive CI/CD pipeline using GitHub Actions for automated building, testing, security scanning, and deployment to Google Cloud Run.

## Features
- **User Management:** Full CRUD operations, activation/deactivation, and retrieval of a user's loans and reservations.
- **Book Management:** Full CRUD operations, inventory control (add/remove stock), and status management.
- **Loan System:** Create loans for users, process book returns, and cancel active loans.
- **Reservation System:** Allow users to reserve available books.
- **Fine Management:** Automatically generates fines for overdue loans and allows for payment processing.
- **API Documentation:** Self-documented API using SpringDoc, providing an interactive Swagger UI.
- **CI/CD:** Automated pipeline with GitHub Actions for building, testing (unit tests & Checkstyle), security scanning (Trivy), and deploying to Google Cloud Run.

## Tech Stack
- **Backend:** Java 17, Spring Boot 3.4.2 (with Spring Web, Spring Data JPA, Spring Validation)
- **Database:** H2 (for local development), PostgreSQL (supported)
- **Build:** Apache Maven
- **API Documentation:** SpringDoc (Swagger UI)
- **Containerization:** Docker
- **CI/CD:** GitHub Actions, Google Artifact Registry, Google Cloud Run

## Project Structure

The project follows a layered architecture commonly used in Spring Boot
applications, separating concerns and improving maintainability.

```sh
library-ops/
├── .github/
│   ├── actions/
│   │   ├── cache-maven/
│   │   │   └── action.yml              # Reusable action for Maven dependency caching
│   │   └── upload-artifact/
│   │       └── action.yml              # Reusable action for build artifacts handling
│   └── workflows/
│       ├── build-java.yml              # Application build workflow
│       ├── test-java.yml               # Unit tests and quality checks
│       ├── scan-security.yml           # Trivy security scanning
│       ├── docker-publish.yml          # Docker build and push
│       └── main.yml                    # CI/CD pipeline orchestration
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── heitor/
│   │   │           └── app/
│   │   │               ├── controller/        # REST controllers (API layer)
│   │   │               ├── dto/               # Data Transfer Objects
│   │   │               │   ├── input/          # Request payloads
│   │   │               │   ├── output/         # Response payloads
│   │   │               │   └── common/         # Shared DTO structures
│   │   │               ├── entity/             # JPA entities (domain model)
│   │   │               ├── enums/              # Domain enumerations
│   │   │               ├── exception/          # Custom application exceptions
│   │   │               ├── handler/            # Global exception handling
│   │   │               ├── mapper/             # Entity ↔ DTO mappers
│   │   │               ├── repository/         # Spring Data JPA repositories
│   │   │               └── service/            # Business logic layer
│   │   │                   └── impl/           # Service implementations
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/                               # Unit and integration tests
│
├── Dockerfile
├── pom.xml
├── README.md
├── CONTRIBUTING.md
└── LICENSE
```

## API Documentation
Once the application is running, the complete API documentation is available via Swagger UI. You can explore all endpoints, view schemas, and test the API directly from your browser.

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Getting Started

### Prerequisites
- Java JDK 17 or later
- Apache Maven

## Installation & Running Locally
1. **Clone the repository:**
```sh
git clone https://github.com/heitordalla/library-ops.git
cd library-ops
```

2. **Run the application:**
The project is configured to use an in-memory H2 database by default, so no additional database setup is required. Use the Maven wrapper to start the application:
```sh
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

*Note: To use PostgreSQL, uncomment the PostgreSQL properties and comment out the H2 properties in the `src/main/resources/application.properties` file.*

## Running with Docker
You can also run the application inside a Docker container.

1. **Build the Docker image:**
```sh
docker build -t library-ops .
```

2. **Run the Docker container:**
```sh
docker run -p 8080:8080 library-ops
```

The application will be accessible at `http://localhost:8080`.

## API Endpoints Overview
The API provides a set of RESTful endpoints to manage library resources. For detailed information, please refer to the [Swagger UI](#api-documentation).

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET, POST | `/users` | Get all users or create a new user. |
| GET, PUT, PATCH | `/users/{id}` | Get, update, or partially update a user. |
| PATCH | `/users/{id}/activate` | Activate a user account. |
| PATCH | `/users/{id}/deactivate` | Deactivate a user account. |
| GET | `/users/{id}/loans` | Get all loans for a specific user. |
| GET | `/users/{id}/reservations` | Get all reservations for a specific user. |

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET, POST | `/books` | Get all books or create a new book. |
| GET, PUT, PATCH | `/books/{id}` | Get, update, or partially update a book. |
| PATCH | `/books/{id}/activate` | Activate a book. |
| PATCH | `/books/{id}/deactivate` | Deactivate a book. |
| PATCH | `/books/{id}/add-stock` | Add copies to a book's stock. |
| PATCH | `/books/{id}/remove-stock` | Remove copies from a book's stock. |

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET, POST | `/loans` | Get all loans or create a new loan. |
| GET, DELETE | `/loans/{id}` | Get a specific loan or cancel it. |
| PUT | `/loans/{id}/return` | Process the return of a loaned book. |

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET, POST | `/reservations` | Get all reservations or create a new one. |
| GET | `/reservations/{id}` | Get a specific reservation. |

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/fines` | Get all fines. |
| GET, PATCH | `/fines/{id}` | Get a specific fine or process its payment. |

## CI/CD Pipeline
This project implements a complete CI/CD pipeline using **GitHub Actions** to automate build, test, security scanning, containerization, and deployment to **Google Cloud Run**.

- **Triggers:** Runs on every `push` and `pull_request` targeting the `main` branch.
- **Jobs:**
  1. **Build**
      - Compiles the application using Maven
      - Packages the application as a JAR

  2. **Test & Quality Checks**
      - Executes unit tests
      - Runs Checkstyle to enforce coding standards

  3. **Security Scanning**
      - Uses Trivy to scan the repository for known vulnerabilities
      - Uploads security results to the GitHub Security tab

  4. **Containerization**
      - Builds a Docker image using the generated JAR
      - Tags the image based on the commit SHA

  5. **Artifact Registry**
      - Authenticates with Google Cloud via service account
      - Pushes the Docker image to Google Artifact Registry

  6. **Deployment**
      - Deploys the container to Google Cloud Run
      - Uses a fully managed, serverless environment
      - Ensures zero-downtime deployment

## Contributing
Contributions are welcome! Please read the `CONTRIBUTING.md` file for details on our code of conduct and the process for submitting pull requests.

## License
This project is licensed under the MIT License – see the `LICENSE` file for details.
