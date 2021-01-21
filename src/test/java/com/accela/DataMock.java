package com.accela;

import com.accela.api.generated.models.AddressDTO;
import com.accela.api.generated.models.PersonDTO;
import com.accela.model.Address;
import com.accela.model.Person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

public class DataMock {

    public static Person mockValidPersonWithAddresses() {
        final Person person = new Person().toBuilder().id(UUID.randomUUID()).firstName("John").lastName("Doe").build();
        person.setAddresses(new HashSet<>(Arrays.asList(
                Address.builder().person(person).city("City1").street("Street1").state("State1").postalCode("Code1").build(),
                Address.builder().person(person).city("City2").street("Street2").state("State2").postalCode("Code2").build()
        )));
        return person;
    }

    public static PersonDTO mockValidPersonDTOWithAddresses() {
        final PersonDTO personDTO = new PersonDTO().firstName("John").lastName("Doe");
        personDTO.setAddresses(Arrays.asList(
                new AddressDTO().city("City1").street("Street1").state("State1").postalCode("Code1"),
                new AddressDTO().city("City2").street("Street2").state("State2").postalCode("Code2")
        ));
        return personDTO;
    }

    public static AddressDTO mockValidAddressDTO() {
        return new AddressDTO().city("City1").street("Street1").state("State1").postalCode("Code1");
    }
}
