## Overview

This is a Customer Service application built using Spring Boot. It is designed to manage customer data with functionalities to create, read, update, and delete customer information. The application uses Oracle Database for data storage and includes basic security configurations.

## Prerequisites

   - Java 17 or higher
   - Maven 3.6.0 or higher
   - Docker (for running Oracle Database in a container)

## Setup and Installation

1. **Clone the repository:**

    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Run Oracle Database in Docker:**

    - **Pull the Oracle Database Docker image:**

        ```bash
        docker pull store/oracle/database-enterprise:12.2.0.1
        ```

    - **Run the Oracle Database container:**

        ```bash
        docker run -d -p 1521:1521 -p 5500:5500 --name oracledb \
        -e ORACLE_PWD=<your-password> \
        store/oracle/database-enterprise:12.2.0.1
        ```

    - **Verify the Oracle Database container is running:**

        ```bash
        docker ps
        ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

## Usage

- Access the application at `http://localhost:8080`.
