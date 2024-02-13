# API Documentation

## Endpoint: POST /scrap-grades

This endpoint is used to calculate the GPA for the given courses.

### Request

#### Body

- `page`: String (required) - The HTML page content For the grades page on the [Bin Al-Haytham](https://stdch.menofia.education/static/PortalStudent.html) website to be parsed for course data.
- `grades`: String (optional) - A comma-separated list of grades for unannounced courses. To pass the grades, include them in the URL as a query parameter like this: `http://localhost:8080/scrap-grades?grades=90,85,80`

### Response

The response will be a JSON object with the following properties:

- `termGPA`: double - The calculated term GPA.
- `termGrade`: String - The letter grade corresponding to the term GPA.
- `cumulativeGPA`: double - The calculated cumulative GPA.
- `cumulativeGrade`: String - The letter grade corresponding to the cumulative GPA.

### Example

#### Request

```json
{
  "page": "<html>...</html>",
  "grades": "90,85,80"
}
```

#### Response

```json
{
  "termGPA": 3.67,
  "termGrade": "A-",
  "cumulativeGPA": 3.33,
  "cumulativeGrade": "B+"
}
```

### Error Response

In case of an error, the response will be a JSON object with the following properties:

- `code`: int - The error code.
- `message`: String - The error message.
- `timeStamp`: String - The timestamp when the error occurred.
- `desc`: String - The description of the error.

#### Example

```json
{
  "code": 400,
  "message": "Bad Request",
  "timeStamp": "2023-12-01T18:30:00.000+00:00",
  "desc": "Invalid page content."
}
```
