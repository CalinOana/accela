package com.accela.service;

import com.accela.BaseServiceIntTest;
import com.accela.DataMock;
import com.accela.api.generated.models.PersonDTO;
import com.accela.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        personRepository.save(DataMock.mockValidPersonWithAddresses());
        final List<PersonDTO> persons = assertDoesNotThrow(() -> personService.getPersons());
        assertEquals(1, persons.size());

        Person person = modelMapperExtended.map(persons.get(0), Person.class);
        BeanUtils.copyProperties(persons.get(0), person);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(2, person.getAddresses().size());
    }
}
