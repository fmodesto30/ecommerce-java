
# Java Spring Boot Application that Search products by ASIN at AMAZON online store.

## Introduction

This application serves as a tool to search for Amazon products using the ASIN code. Developed using Spring Boot (2.1.5.RELEASE), it provides a RESTful API.

## Setup

### Prerequisites

- Java JDK (version 11 or higher)
- Maven (version 3.6.0 or higher)

### Environment Setup

1. Clone the repository: `git clone git@github.com:fmodesto30/ecommerce-java.git`
2. Navigate to the root of the project directory: `cd ecommerce-java`
3. Install dependencies: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Alternatively, you can run the application using an IDE. Simply navigate to Application.java and run it as a Java Application.

### Database Setup

This project is configured to use H2, an in-memory database that replicates an SQL/PostgreSQL database with the same SQL syntax query, without needing to create a server database. Learn more about H2 [here](https://www.h2database.com/html/main.html).
 
## Running the Application

After completing the setup, the application can be run with the command: `mvn spring-boot:run`

By default, the application will start on port 8080. 

You can access it at: `http://localhost:8080`

## Testing

Run the test suite with the command: `mvn test`. This will execute Junit tests covering the methods used for API requests.

## API Documentation

API endpoints and their details can be accessed and tested via the Swagger UI, under the "Product Controller" section:

http://localhost:8080/swagger-ui.html#/

## Product Advertising API 

Due to certain limitations with Amazon's PA API (requiring 3 qualified sales in 180 days, an approved Associate account, and compliance with the Affiliate Program Operating Agreement), this application uses HTML scraping to retrieve product details from Amazon's official URLs (amazon.com and amazon.com.br (Local)).

