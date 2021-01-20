package com.accela;

import com.accela.model.Address;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@SpringBootApplication(scanBasePackages = {
        "com.accela",
        "com.accela.api.generated"
})
@RequiredArgsConstructor
public class AccelaApplication {

    private final PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(AccelaApplication.class, args);
    }

    @PostConstruct
    public void started() {
        createInitialDataset();
    }

    private void createInitialDataset() {
        final Person person = new Person().toBuilder().id(UUID.randomUUID()).firstName("John").lastName("Doe").build();
        person.setAddresses(new HashSet<>(Arrays.asList(
                Address.builder().person(person).city("City1").street("Street1").state("State1").postalCode("Code1").build(),
                Address.builder().person(person).city("City2").street("Street2").state("State2").postalCode("Code2").build()
                )));
        personRepository.save(person);
    }
}
