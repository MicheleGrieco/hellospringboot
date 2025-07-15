# HelloSpringBoot

This is a Spring Boot project demonstrating how to build a REST API using Java, Spring Boot, and PostgreSQL. It also integrates with OpenAI using Spring AI to provide AI-powered recommendations.

## Project Structure

- `src/main/java/com/example/hellospringboot/` - Main Java source code
  - `SoftwareEngineer.java` - Entity representing a software engineer
  - `SoftwareEngineerRepository.java` - JPA repository for CRUD operations
  - `SoftwareEngineerService.java` - Business logic for managing software engineers
  - `SoftwareEngineerController.java` - REST API endpoints
  - `AIService.java` - Service for interacting with OpenAI
- `src/main/resources/` - Application configuration files
  - `application.properties` - Database and AI configuration
- `src/test/java/com/example/hellospringboot/` - Test classes
  - `SoftwareEngineerServiceTests.java` - Unit tests for the service layer
- `pom.xml` - Maven build configuration

## Main Dependencies

- Spring Boot Web
- Spring Data JPA
- PostgreSQL Driver
- Spring AI (OpenAI integration)
- JUnit & Mockito (for testing)

## How to Run

1. Make sure you have Java 17+ and Maven installed.
2. Set up a PostgreSQL database and update the credentials in `src/main/resources/application.properties` if needed.
3. Set the environment variable `OPEN_AI_KEY` with your OpenAI API key.
4. In the project root, run:
   ```
   mvn spring-boot:run
   ```
5. The application will start on [http://localhost:8080](http://localhost:8080)

## Example REST Endpoints

- `GET /hello` - Returns a simple greeting message.
- `GET /api/v1/software-engineers` - List all software engineers.
- `POST /api/v1/software-engineers` - Add a new software engineer (with AI-powered learning path recommendation).
- `PUT /api/v1/software-engineers/{id}` - Update an existing software engineer.
- `DELETE /api/v1/software-engineers/{id}` - Delete a software engineer by ID.

## How to Extend

- Add new REST controllers in `src/main/java/com/example/hellospringboot/controller/`
- Add business logic in `service/`
- Define data models in `model/`
- Add new tests in `src/test/java/com/example/hellospringboot/`

## Configuration

- Database connection and JPA settings are in `src/main/resources/application.properties`.
- OpenAI API key is read from the `OPEN_AI_KEY` environment variable.

## Testing

- Run unit tests with:
  ```
  mvn test
  ```
- Tests use Mockito to mock dependencies and JUnit for assertions.

## License

This project is for learning purposes.