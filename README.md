Customer Service Application
Overview

This is a Customer Service application built using Spring Boot. It is designed to manage customer data with functionalities to create, read, update, and delete customer information. The application uses Oracle Database for data storage and includes basic security configurations.
Project Structure
1. SecurityConfig.java

This file contains the security configuration for the application. It sets up basic authentication and authorization rules to secure the endpoints.
2. CustomerController.java

This is the REST controller for managing customer data. It handles HTTP requests and maps them to the corresponding service methods.
3. CustomerDTO.java

Data Transfer Object (DTO) for customer data. This class is used to transfer data between the layers of the application.
4. CustomerMapper.java

This class is responsible for mapping Customer entities to CustomerDTO and vice versa. It facilitates the conversion between the data transfer object and the entity.
5. Customer.java

This is the entity class that represents the customer data in the database. It is annotated with JPA annotations to map the class to a database table.
6. CustomerRepository.java

This interface extends JpaRepository and provides CRUD operations for the Customer entity. It allows interaction with the database.
7. CustomerService.java

This class contains the business logic for managing customers. It calls the repository methods to perform database operations and contains additional business rules.
8. application.properties

This file contains the configuration properties for the Spring Boot application. It includes database connection details and other configurations.
Prerequisites

    Java 8 or higher
    Maven 3.6.0 or higher
    Oracle Database

Setup and Installation

    Clone the repository:

    bash

git clone <repository-url>
cd <repository-directory>

Configure the database:
Ensure that your Oracle Database is running and the connection details in the application.properties file are correct:

properties

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCLCDB
spring.datasource.username=system
spring.datasource.password=oracle
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.hibernate.ddl-auto=create-drop

Build the project:

bash

mvn clean install

Run the application:

bash

    mvn spring-boot:run

Usage

    Access the application at http://localhost:8080.
    Use Postman or any other API client to test the endpoints.

Example Endpoints

    GET /customers - Retrieve all customers
    POST /customers - Create a new customer
    PUT /customers/{id} - Update an existing customer
    DELETE /customers/{id} - Delete a customer

Logging

The logging configuration is set to debug SQL and trace types:

properties

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE

Security

Basic security configurations are set in the SecurityConfig.java file. Modify this file to change security settings as per your requirements.
