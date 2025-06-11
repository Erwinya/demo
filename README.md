# Demo Spring Boot User Management Project

This project is a user management REST API built with Spring Boot, following SOLID principles and Clean Code practices. It provides endpoints for user registration and user retrieval, and is designed for maintainability, scalability, and clarity.

## Features
- Register new users (with unique username constraint)
- Retrieve user information by username
- Standardized API responses
- Exception handling for validation and not found errors
- PostgreSQL database integration
- Docker support for easy setup

## Project Structure
```
├── controller/         # Handles HTTP requests (UserController)
├── dto/                # Data Transfer Objects (UserDTO)
├── entity/             # JPA Entities (User)
├── exception/          # Custom exceptions and global handler
├── mapper/             # Maps between DTO and Entity (UserMapper)
├── repository/         # Spring Data JPA repositories (UserRepository)
├── response/           # Standard API response wrapper (CustomResponse)
├── service/            # Service interfaces (UserService)
├── serviceImpl/        # Service implementations (UserServiceImpl)
├── resources/          # Configuration files (application.yaml)
├── docker-compose.yaml # Docker setup for PostgreSQL
```

## SOLID Principles in This Project
- **Single Responsibility Principle:** Each class has one responsibility (e.g., controller only handles HTTP, service only business logic).
- **Open/Closed Principle:** Classes are open for extension but closed for modification (e.g., service interfaces, exception handling).
- **Liskov Substitution Principle:** Service implementations can replace their interfaces without breaking the system.
- **Interface Segregation Principle:** Interfaces are specific and not bloated (UserService is focused on user operations).
- **Dependency Inversion Principle:** High-level modules depend on abstractions (UserService), not concrete implementations.

## Clean Code Practices
- **Descriptive Naming:** Classes, methods, and variables have clear, meaningful names.
- **Short Methods:** Methods do one thing and are concise.
- **Error Handling:** Custom exceptions and global handler provide clear error messages.
- **DTO Usage:** Separates API models from database entities for security and clarity.
- **Consistent Formatting:** Code is consistently formatted and organized.

## How to Run
1. **Start PostgreSQL with Docker:**
   ```bash
   docker-compose up -d
   ```
2. **Configure Database:**
   Edit `src/main/resources/application.yaml` if needed for your environment.
3. **Build and Run the Application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints
- **POST /api/users**
  - Registers a new user.
  - Request body: `{ "username": "john", "name": "John", "surname": "Doe" }`
  - Returns: Success message or 400 error if username exists.

- **GET /api/users/{username}**
  - Retrieves user info by username.
  - Returns: User data or 404 if not found.

## Error Handling
- Returns 400 Bad Request if username is not unique or input is invalid.
- Returns 404 Not Found if user does not exist.
- All errors are returned in a standard JSON format.

## Example Response
```json
{
  "data": {
    "username": "john",
    "name": "John",
    "surname": "Doe"
  },
  "statusCode": 200,
  "statusMessage": "SUCCESS",
  "timestamp": "2025-06-10T12:00:00Z"
}
```

## Testing
- Use Postman or curl to test endpoints.
- Example:
  ```bash
  curl -X POST http://localhost:8080/api/users \
    -H "Content-Type: application/json" \
    -d '{"username":"john","name":"John","surname":"Doe"}'
  ```

## License
This project is for educational and demonstration purposes.
