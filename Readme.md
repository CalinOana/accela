# Accela Project Backend

## Test
* Run all unit tests: `mvn test`

## Build
* Run maven build: `mvn clean install`
* Generated artifacts in ./target

## Run
* Run spring boot application using maven: `./mvnw spring-boot:run`

## Endpoints
* http://localhost:8082/persons to list all persons

# Database
* To access the database console (while application is running):
  * `http://localhost:8082/h2-console`
  * `Generic H2 (Server)` from Saved Settings menu
  * `jdbc:h2:mem:myDb` for JDBC URL
  * `accela` for User Name and Password

