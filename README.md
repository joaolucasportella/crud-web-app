# CRUD Web Application

## Overview

The CRUD Web Application is a Java-based web application designed to manage a simple customer table. Built with **Spring Boot 3.4.0**, it follows a clean architecture using **MVC (Model-View-Controller)** principles. The application supports operations for creating, reading, updating, and deleting customer records, and is configured to run on a Tomcat server with PostgreSQL as the database.

## Features

- **CRUD Operations**: Manage customer data with full Create, Read, Update, and Delete functionalities.
- **Data Validation**: Ensures data integrity through `jakarta.validation` constraints.
- **Thymeleaf Templating**: Provides interactive and responsive HTML templates.
- **Custom Annotations**: Implements unique customer validation using a custom annotation.
- **Responsive Design**: Enhanced user experience with dynamic styles and JavaScript.

## Configuration

### Create the `Customer` Table

To set up the `Customer` table in your PostgreSQL database, execute the following SQL DDL:

```sql
CREATE TABLE Customer (
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL,
    phone VARCHAR(11) UNIQUE NOT NULL,
    cep VARCHAR(8) NOT NULL,
    state VARCHAR(2) NOT NULL,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
```

### Database Credentials

Configure your PostgreSQL database credentials by creating an `application-secrets.yml` file in the `src/main/resources/` directory. Below is an example configuration:

```yml
spring:
  datasource:
    url: jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
    username: <USERNAME>
    password: <PASSWORD>
```

## How to Run

1. **Prerequisites**:

   - Java 21 installed.
   - Maven installed.
   - PostgreSQL server up and running.

2. **Build**:

   ```bash
   mvn clean install
   ```

3. **Run**:

   ```bash
   mvn spring-boot:run
   ```

4. Access the application at: [http://localhost:8080](http://localhost:8080)

## License

This project is licensed under the [Apache License 2.0](LICENSE).
