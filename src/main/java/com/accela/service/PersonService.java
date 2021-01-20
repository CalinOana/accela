package com.accela.service;

import com.accela.api.generated.models.PersonDTO;
import com.accela.components.cast.ModelMapperExtended;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapperExtended modelMapperExtended;

    @Transactional
    public List<PersonDTO> getPersons() {
        final List<Person> all = personRepository.findAll();
        return modelMapperExtended.mapAll(all,PersonDTO.class);
    }
}
