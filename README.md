## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Docker (for running Oracle Database in a container)

## Setup and Installation

1. **Run Oracle Database in Docker:**

    - **Log in with your oracle account to Oracle Container Registry:**

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

2. **Clone the repository:**

    ```bash
    git clone [<repository-url>](https://github.com/kissszilvi12/CustomerService.git)
    cd CustomerService
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
