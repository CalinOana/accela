package com.accela.web;

import com.accela.api.generated.controllers.PersonsApi;
import com.accela.api.generated.models.PersonDTO;
import com.accela.service.PersonService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class PersonsController implements PersonsApi {
    private final PersonService personService;

    @Override
    public ResponseEntity<List<PersonDTO>> personsGet() {
        final List<PersonDTO> persons = personService.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonDTO> personsPost(@ApiParam(value = "Body containing PersonDTO to add", required = true)
                                                 @Valid @RequestBody PersonDTO personDTO) {

        return new ResponseEntity<>(personService.createPerson(personDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonDTO> editPerson(@ApiParam(value = "Body containing PersonDTO to edit", required = true)
                                                @Valid @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.editPerson(personDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePerson(@ApiParam(value = "ID of the Person to delete", required = true) @PathVariable("id") UUID id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<BigDecimal> personsCountGet() {
        return new ResponseEntity<>(personService.getPersonsCount(), HttpStatus.OK);
    }
}
