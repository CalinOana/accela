package com.accela.repository;

import com.accela.BaseServiceIntTest;
import com.accela.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static com.accela.AssertUils.filterAddressList;
import static com.accela.DataMock.mockValidPersonWithAddresses;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.util.CollectionUtils.isEmpty;


class PersonRepositoryIntTest extends BaseServiceIntTest {


    @DisplayName("When saving a person to repository assert person is created with correct fields")
    @Transactional
    @Rollback
    @Test
    void savePersonTest() {
        personRepository.save(mockValidPersonWithAddresses());

        final List<Person> persons = personRepository.findAll();

        assertFalse(isEmpty(persons));

        Person person = persons.get(0);
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());

        assertEquals(2, person.getAddresses().size());
        assertEquals(1, filterAddressList(person, "City1", "Street1", "State1", "Code1").size());
        assertEquals(1, filterAddressList(person, "City2", "Street2", "State2", "Code2").size());
    }


}
