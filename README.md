# HelloSpringBoot

This is a simple Spring Boot project demonstrating how to build a REST API using Java and Spring Boot.

## Project Structure

- `src/main/java/com/example/hellospringboot/` - Main Java source code
- `src/main/resources/` - Application configuration files
- `src/test/java/com/example/hellospringboot/` - Test classes
- `pom.xml` - Maven build configuration

## How to Run

1. Make sure you have Java 17+ and Maven installed.
2. Clone this repository or download the source code.
3. In the project root, run:
   ```
   mvn spring-boot:run
   ```
4. The application will start on [http://localhost:8080](http://localhost:8080)

## Example REST Endpoint

- `GET /hello` - Returns a simple greeting message.

## How to Extend

- Add new REST controllers in `src/main/java/com/example/hellospringboot/controller/`
- Add business logic in `service/`
- Define data models in `model/`

## License

This project is for learning purposes.