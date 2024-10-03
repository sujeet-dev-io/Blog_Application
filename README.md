1. JWT (JSON Web Token) Implementation
JWT Generation and Validation: Created utility classes for generating and validating JWT tokens.
Custom Filter: Implemented a filter to validate JWT for secure API access.
Security Configuration: Set up JWT-based authentication in Spring Security without WebSecurityConfigurerAdapter.
2. Exception Handling
Global Exception Handler: Used @ControllerAdvice for centralized exception handling.
Custom Exceptions: Defined custom exceptions like ResourceNotFoundException and InvalidInputException.
Meaningful Responses: Provided informative error messages with @ExceptionHandler.
3. Validation
DTO Validation: Implemented validation annotations in DTOs (e.g., @NotBlank, @Email, @Pattern) for input data.
Custom Validation Messages: Customized validation error messages for a better client response.
4. Database Integration
MySQL Integration: Used MySQL for managing relational data (users, posts, comments).
MongoDB Integration: Integrated MongoDB for non-relational data storage.
JPA and Mongo Repositories: Implemented both JPA and Mongo repositories to interact with respective databases.
5. Swagger UI Integration
API Documentation: Integrated Swagger UI for easy testing and documentation of API endpoints.
Access Point: Available at http://localhost:8080/swagger-ui.html.
6. Other Java Concepts
Dependency Injection: Used Spring’s @Autowired for injecting dependencies.
Exception Handling: Handled exceptions with try-catch and custom exception classes.
Inheritance & Polymorphism: Applied OOP concepts such as inheritance and polymorphism throughout the application.
Interfaces and Abstraction: Utilized interfaces for service abstraction and clean architecture.
File Uploads: Integrated AWS S3 for handling image uploads and file storage.
