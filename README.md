# URL Shortener

A simple URL shortener built with Spring Boot.

## Features
- Shorten long URLs.
- Redirect to the original URL using the short URL.

## How to Run
1. Clone the repository.
2. Run the application using `mvn spring-boot:run`.
3. Access the APIs at `http://localhost:8080/api`.

## API Endpoints
- `POST /api/shorten`: Shorten a URL.
- `GET /api/{shortUrl}`: Redirect to the original URL.