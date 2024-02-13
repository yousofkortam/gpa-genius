# GPA Genius

This project is a Spring Boot application that provides an API endpoint to calculate the GPA for given courses. The application is written in Java and uses Maven for dependency management.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Maven
- IntelliJ IDEA 2023.2.5 or any other IDE that supports Spring Boot

### Installing

1. Clone the repository to your local machine.
2. Open the project in your IDE.
3. Run `mvn clean install` to build the project and install the dependencies.

## Running the Application

You can run the application from your IDE by running the `main` method in the `Application` class. Alternatively, you can use the Spring Boot Maven plugin to run the application by executing the following command in the terminal:

```bash
mvn spring-boot:run
```

## API Documentation

### Endpoint: POST /scrap-grades

This endpoint is used to calculate the GPA for the given courses from the [Bin Al-Haytham](https://www.binalhaytham.edu.sa/) website.

#### Request

##### Body

- `page`: String (required) - The HTML page content of the grades page on the [Bin Al-Haytham](https://www.binalhaytham.edu.sa/) website to be parsed for course data. You can pass the HTML content as text in the body of a POST request. For example, using `curl`:

```bash
curl -X POST -H "Content-Type: text/plain" -d "<html>...</html>" http://localhost:8080/scrap-grades?grades=90,85,80
```

Replace `<html>...</html>` with your actual HTML content of the grades page. This command sends a POST request to the `/scrap-grades` endpoint with the HTML content in the body and the grades as a query parameter.

- `grades`: String (optional) - A comma-separated list of grades for unannounced courses. To pass the grades, include them in the URL as a query parameter like this: `http://localhost:8080/scrap-grades?grades=90,85,80`.

#### Response

The response will be a JSON object with the following properties:

- `termGPA`: double - The calculated term GPA.
- `termGrade`: String - The letter grade corresponding to the term GPA.
- `cumulativeGPA`: double - The calculated cumulative GPA.
- `cumulativeGrade`: String - The letter grade corresponding to the cumulative GPA.

#### Example

##### Request

```json
{
  "page": "<html>...</html>",
  "grades": "90,85,80"
}
```

##### Response

```json
{
  "termGPA": 3.67,
  "termGrade": "A-",
  "cumulativeGPA": 3.33,
  "cumulativeGrade": "B+"
}
```

#### Error Response

In case of an error, the response will be a JSON object with the following properties:

- `code`: int - The error code.
- `message`: String - The error message.
- `timeStamp`: String - The timestamp when the error occurred.
- `desc`: String - The description of the error.

##### Example

```json
{
  "code": 400,
  "message": "Bad Request",
  "timeStamp": "2023-12-01T18:30:00.000+00:00",
  "desc": "Invalid page content."
}
```

## Built With

- [Java](https://www.java.com/) - The programming language used
- [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
- [Maven](https://maven.apache.org/) - Dependency Management

## Authors

- [yousofkortam](https://github.com/yousofkortam) - Initial work
