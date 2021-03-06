# Accela Project Backend

## Test
* Run all unit tests: `mvn test`

## Build
* Run maven build: `mvn clean install`
* Generated artifacts in ./target

## Run
* Run spring boot application using maven: `./mvnw spring-boot:run`

## Endpoints
* Best tested using Postman utility
  * Call http://localhost:8082/persons with verb:
    * GET    ![getAllPersonsImage.png](getAllPersonsImage.png) to list all persons 
    * POST  ![postPersonImage.png](postPersonImage.png), with a JSON body like the example, to add a new person 
    * PUT  ![editPersonById.png](editPersonById.png), with a JSON body like the example (make sure id field exists, for example by calling GET), to edit an existing person 
  * Call http://localhost:8082/persons/{id} with verb:
    * DELETE    ![deletePersonById.png](deletePersonById.png) to delete a person having id as in param
  * Call http://localhost:8082/persons/count with verb:
    * GET    ![personsCount.png](personsCount.png) to get the number of persons saved
  * Call http://localhost:8082/persons/{id}/appendAddresses with verb:
    * PUT    ![appendAddresses.png](appendAddresses.png), with a JSON body like the example, to append addresses to an existing person
  * Call http://localhost:8082/addresses/{id} with verb:
    * DELETE   ![deleteAddress.png](deleteAddress.png) to delete an address having id as in param
  * Call http://localhost:8082/addresses with verb:
    * PUT  ![putAddress.png](putAddress.png), with a JSON body like the example (make sure id field exists, for example by calling GET), to edit an existing address
  
# Database
* To access the database console (while application is running):
  * `http://localhost:8082/h2-console`
  * `Generic H2 (Server)` from Saved Settings menu
  * `jdbc:h2:mem:myDb` for JDBC URL
  * `accela` for User Name and Password

