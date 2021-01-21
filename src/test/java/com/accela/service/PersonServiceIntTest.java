package com.accela.service;

import com.accela.BaseServiceIntTest;
import com.accela.DataMock;
import com.accela.api.generated.models.AddressDTO;
import com.accela.api.generated.models.PersonDTO;
import com.accela.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static com.accela.DataMock.mockValidPersonWithAddresses;
import static org.junit.jupiter.api.Assertions.*;

class PersonServiceIntTest extends BaseServiceIntTest {

    @BeforeEach
    void setup() {
        personRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Given call to getPersons with a person and an address in repository assert does not fail and users are correctly saved")
    void getPersonsTest() {
        personRepository.save(mockValidPersonWithAddresses());
        final List<PersonDTO> persons = assertDoesNotThrow(() -> personService.getPersons());
        assertEquals(1, persons.size());

        Person person = modelMapperExtended.map(persons.get(0), Person.class);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(2, person.getAddresses().size());
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Given call to createPerson  assert does not fail and person is correctly saved")
    void createPersonTest() {
        final PersonDTO person = assertDoesNotThrow(() -> personService.createPerson(DataMock.mockValidPersonDTOWithAddresses()));
        assertNotNull(person);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(2, person.getAddresses().size());
        assertTrue(person.getAddresses().stream().anyMatch(addressDTO -> addressDTO.getCity().equals("City1") || addressDTO.getStreet().equals("Street2")));
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Given call to appendAddressesToPErson  assert does not fail and person is correctly updated")
    void appendAddressesToPErsonTest() {
        final Person savedPerson = personRepository.save(mockValidPersonWithAddresses());
        final AddressDTO a = DataMock.mockValidAddressDTO();

        final PersonDTO person = assertDoesNotThrow(() -> {
            return personService.appendAddressesToPErson(savedPerson.getId(), Collections.singletonList(a));
        });
        assertNotNull(person);
        assertEquals(3,person.getAddresses().size());
        assertTrue(person.getAddresses().stream().anyMatch(addressDTO -> addressDTO.getCity().equals(a.getCity())
                && addressDTO.getStreet().equals(a.getStreet())
                && addressDTO.getState().equals(a.getState())
                && addressDTO.getPostalCode().equals(a.getPostalCode())
        ));
    }
}
