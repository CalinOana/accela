package com.accela.service;

import com.accela.api.generated.models.PersonDTO;
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
        personService = new PersonService(personRepositoryMock, new ModelMapperExtended(new ModelMapper()),null);
    }

    @Test
    @DisplayName("Given person with addresses added to the collection when linkAddressesToPerson assert addresses are linked to the person they belong to")
    void linkAdddressesToPersonTest() {
        final Person person = new Person();
        person.getAddresses().addAll(Arrays.asList(new Address(), new Address()));
        personService.linkAdressesToPerson(person);

        person.getAddresses().forEach(address -> {
            assertEquals(person, address.getPerson());
        });
    }
    @Test
    @DisplayName("Given PersonDTO with firstname and lastname filled when copyFirstAndLastName assert fields are copied correctly")
    void copyFirstAndLastNameTest() {
        final Person person = new Person();
        personService.copyFirstAndLastName(new PersonDTO().firstName("John").lastName("Dover"),person);
        assertEquals("John",person.getFirstName());
        assertEquals("Dover",person.getLastName());
    }
}
