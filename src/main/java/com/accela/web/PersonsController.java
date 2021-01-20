package com.accela.web;

import com.accela.api.generated.controllers.PersonsApi;
import com.accela.api.generated.models.PersonDTO;
import com.accela.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
