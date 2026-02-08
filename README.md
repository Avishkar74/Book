# Library Management System

A Spring Boot application for managing a library's book inventory and operations.

## Overview

This is a full-stack web application built with Spring Boot that allows users to manage books in a library system. It provides CRUD operations for books with a user-friendly interface and database persistence.

## Tech Stack

- **Backend**: Spring Boot 3.x
- **Database**: H2 (Development), PostgreSQL (Production)
- **Frontend**: Thymeleaf, Bootstrap
- **Build Tool**: Maven
- **Java Version**: 17+

## Project Structure

```
src/
├── main/
│   ├── java/com/Avishkar/libraryManagementSystem/
│   │   ├── config/              # Configuration classes
│   │   ├── controller/          # REST controllers
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── entity/              # JPA entities
│   │   ├── mapper/              # Entity mappers
│   │   ├── repository/          # Data access layer
│   │   ├── service/             # Business logic
│   │   └── LibraryManagementSystemApplication.java
│   └── resources/
│       ├── application.yaml     # Default configuration
│       ├── application-dev.yaml # Development configuration
│       ├── application-prod.yaml# Production configuration
│       └── templates/           # HTML templates
└── test/
    └── java/                    # Unit tests
```

## Features

- ✅ Create, Read, Update, Delete (CRUD) operations for books
- ✅ Search and filter books
- ✅ Web-based user interface
- ✅ H2 Console for development database inspection
- ✅ Profile-based configuration (dev/prod)
- ✅ PostgreSQL support for production

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Avishkar74/Book.git
cd libraryManagementSystem
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Configuration

#### Development (Default)
To run with H2 in-memory database:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

Access H2 Console: `http://localhost:8080/h2-console`

#### Production
To run with PostgreSQL:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

Make sure to configure your PostgreSQL credentials in `application-prod.yaml`

## API Endpoints

- `GET /books` - List all books
- `GET /books/{id}` - Get a specific book
- `POST /books` - Create a new book
- `PUT /books/{id}` - Update a book
- `DELETE /books/{id}` - Delete a book

## Database

### H2 (Development)
- In-memory database, no setup required
- Auto-creates schema on startup
- Console accessible at `/h2-console`

### PostgreSQL (Production)
Update `application-prod.yaml` with your PostgreSQL credentials:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/library
    username: your_username
    password: your_password
```

## Building

```bash
# Clean build
mvn clean install

# Run tests
mvn test

# Build WAR/JAR
mvn package
```

## Contributing

1. Create a feature branch (`git checkout -b feature/AmazingFeature`)
2. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
3. Push to the branch (`git push origin feature/AmazingFeature`)
4. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Author

Avishkar - [GitHub Profile](https://github.com/Avishkar74)

## Support

For issues and questions, please open an issue on [GitHub Issues](https://github.com/Avishkar74/Book/issues)
