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

    - **Log in to Oracle Container Registry:**

        ```bash
        docker login container-registry.oracle.com
        ```

    - **Pull the Oracle Database Docker image:**

        ```bash
        docker pull container-registry.oracle.com/database/enterprise:latest
        ```

    - **Run the Oracle Database container:**

        ```bash
        docker run -d -p 1521:1521 -p 5500:5500 --name oracledb -e ORACLE_PWD=oracle container-registry.oracle.com/database/enterprise:latest
        ```

    - **Verify the Oracle Database container is running:**

        ```bash
        docker ps
        ```

3. **Configure the database:**
   
   Ensure that your Oracle Database is running and the connection details in the `application.properties` file are correct:
   
    ```properties
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521/ORCLCDB
    spring.datasource.username=system
    spring.datasource.password=oracle
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
    spring.jpa.properties.hibernate.hbm2ddl.auto=update
    spring.jpa.hibernate.ddl-auto=create-drop
    ```

4. **Build the project:**

    ```bash
    mvn clean install
    ```

5. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

## Usage

- Access the application at `http://localhost:8080`.
- Use Postman or any other API client to test the endpoints.
