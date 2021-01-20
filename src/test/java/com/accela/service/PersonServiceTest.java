package com.accela.service;

import com.accela.components.cast.ModelMapperExtended;
import com.accela.model.Address;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonServiceTest {

    PersonService personService;

    @Mock
    PersonRepository personRepositoryMock;

    @BeforeEach
    void setup() {
        personService = new PersonService(personRepositoryMock, new ModelMapperExtended(new ModelMapper()));
    }

    @Test
    @DisplayName("")
    void linkAdddressesToPersonTest() {
        final Person person = new Person();
        person.getAddresses().addAll(Arrays.asList(new Address(), new Address()));
        personService.linkAdressesToPerson(person);

        person.getAddresses().forEach(address -> {
            assertEquals(person, address.getPerson());
        });
    }
}
