package com.accela.service;

import com.accela.api.generated.models.PersonDTO;
import com.accela.components.cast.ModelMapperExtended;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import com.accela.validator.PersonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapperExtended modelMapperExtended;
    private final PersonValidator personValidator;

    @Transactional
    public List<PersonDTO> getPersons() {
        final List<Person> all = personRepository.findAll();
        return modelMapperExtended.mapAll(all, PersonDTO.class);
    }

    @Transactional
    public PersonDTO createPerson(PersonDTO personDTO) {
        personValidator.validatePersonOnCreation(personDTO);
        final Person save = personRepository.save(linkAdressesToPerson(modelMapperExtended.map(personDTO, Person.class)));
        return modelMapperExtended.map(save, PersonDTO.class);
    }

    public Person linkAdressesToPerson(Person person) {
        person.getAddresses().forEach(address -> address.setPerson(person));
        return person;
    }

    @Transactional
    public PersonDTO editPerson(PersonDTO personDTO) {
        personValidator.validatePersonOnEdit(personDTO);

        final Person person = personRepository.findById(personDTO.getId()).get();

        copyFirstAndLastName(personDTO, person);

        return modelMapperExtended.map(personRepository.save(person), PersonDTO.class);
    }

    public void copyFirstAndLastName(PersonDTO personDTO, Person person) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
    }

    @Transactional
    public void deletePerson(UUID id) {
        personValidator.validatePersonOnDelete(id);
        personRepository.delete(personRepository.findById(id).get());
    }
}
