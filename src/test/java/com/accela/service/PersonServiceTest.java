package com.accela.service;

import com.accela.api.generated.models.PersonDTO;
import com.accela.components.cast.ModelMapperExtended;
import com.accela.model.Address;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import com.accela.validator.PersonValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    PersonService personService;

    @Mock
    PersonRepository personRepositoryMock;

    @Mock
    PersonValidator personValidatorMock;

    @BeforeEach
    void setup() {
        personService = new PersonService(personRepositoryMock, new ModelMapperExtended(new ModelMapper()), personValidatorMock);
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
        personService.copyFirstAndLastName(new PersonDTO().firstName("John").lastName("Dover"), person);
        assertEquals("John", person.getFirstName());
        assertEquals("Dover", person.getLastName());
    }

    @Test
    @DisplayName("Given repository with a certain count of items assert getPersonsCount returns correct number of persons in repository")
    void getPersonsCountTest() {
        when(personRepositoryMock.count()).thenReturn(10L);
        assertEquals(BigDecimal.TEN.toString(), personService.getPersonsCount().toString());
    }

}
