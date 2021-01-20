package com.accela.web;

import com.accela.api.generated.controllers.PersonsApi;
import com.accela.api.generated.models.PersonDTO;
import com.accela.service.PersonService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
}
