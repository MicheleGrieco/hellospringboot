# HelloSpringBoot

This is a Spring Boot project demonstrating how to build a REST API using Java, Spring Boot, and PostgreSQL. It also integrates with OpenAI using Spring AI.

## Project Structure

- `src/main/java/com/example/hellospringboot/` - Main Java source code
- `src/main/resources/` - Application configuration files
- `src/test/java/com/example/hellospringboot/` - Test classes
- `pom.xml` - Maven build configuration

## Main Dependencies

- Spring Boot Web
- Spring Data JPA
- PostgreSQL Driver
- Spring AI (OpenAI integration)

## How to Run

1. Make sure you have Java 17+ and Maven installed.
2. Set up a PostgreSQL database and update the credentials in `src/main/resources/application.properties` if needed.
3. Set the environment variable `OPEN_AI_KEY` with your OpenAI API key.
4. In the project root, run:
   ```
   mvn spring-boot:run
   ```
5. The application will start on [http://localhost:8080](http://localhost:8080)

## Example REST Endpoint

- `GET /hello` - Returns a simple greeting message.

## How to Extend

- Add new REST controllers in `src/main/java/com/example/hellospringboot/controller/`
- Add business logic in `service/`
- Define data models in `model/`

## Configuration

- Database connection and JPA settings are in `src/main/resources/application.properties`.
- OpenAI API key is read from the `OPEN_AI_KEY` environment variable.

## License

This project is for learning purposes.