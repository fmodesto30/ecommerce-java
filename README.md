# Java Spring Boot Application for Searching Amazon Products Using ASIN

## Introduction

This application functions as a Scrape tool for searching Amazon products using ASIN codes. It is developed using Spring Boot (2.1.5.RELEASE) and provides a RESTful API interface.

## Setup

The application is built using Maven and is modularized into Domain, Delivery, and Application modules.

### Prerequisites

- Java JDK (version 11 or higher)
- Maven (version 3.6.0 or higher)

### Dealing with Amazon's CAPTCHA and Regional Differences

In developing this application, one challenge encountered was Amazon's implementation of CAPTCHAs on their primary site (amazon.com). CAPTCHAs are designed to verify that the user is human and can cause difficulties when attempting to automate data retrieval via the Java API.

While working from my local IP, using Amazon.com.br was always successful, and I was able to bypass the CAPTCHA. However, when attempting to retrieve data from Amazon.com, I sometimes encountered an IP block. This is an important consideration for users who may need to retrieve data from different regional Amazon sites.

If you are facing the same problems and are outside USA, go to `AmazonReviewScraper.java` and change the `url` domain lines 28 and 29. Ex: amazon.com.`<Your Location>`.

## Product Advertising API 

Due to certain restrictions on Amazon's PA API, such as requiring 3 qualified sales in 180 days, an approved Associate account, and compliance with the Affiliate Program Operating Agreement, this application employs HTML scraping to retrieve product details from Amazon's official URLs (amazon.com and amazon.com.br (Local)).

### Environment Setup

1. Clone the repository: `git clone git@github.com:fmodesto30/ecommerce-java.git`. Make sure to use the `develop-marketplace` branch.
2. Navigate to the root of the project directory: `cd ecommerce-java`
3. Install dependencies: `mvn clean install`
4. Run the application: `mvn spring-boot:run`
5. Alternatively, you can run the application using an IDE by navigating to `Application.java` and running it as a Java Application.

### Database Setup

This project uses H2, an in-memory database that mimics an SQL/PostgreSQL database using the same SQL syntax query, eliminating the need to set up a separate server database. Learn more about H2 [here](https://www.h2database.com/html/main.html).

## Running the Application

Once setup is complete, run the application using the command: `mvn spring-boot:run`

By default, the application starts on port 8080. 

Access it at: `http://localhost:8080`

## Testing

Run the test suite using the command: `mvn test`. This will execute JUnit tests that cover the methods used for API requests.

## API Documentation

You can access and test API endpoints and their details via the Swagger UI, under the "Product Controller" section:

http://localhost:8080/swagger-ui.html#/

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.
