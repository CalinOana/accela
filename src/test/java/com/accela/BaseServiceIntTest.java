package com.accela;

import com.accela.components.cast.ModelMapperExtended;
import com.accela.repository.PersonRepository;
import com.accela.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccelaApplication.class)
public class BaseServiceIntTest {

    @Autowired
    public PersonRepository personRepository;

    @Autowired
    public PersonService personService;

    @Autowired
    public ModelMapperExtended modelMapperExtended;

    public MockMvc mockMvc;

    @Test
    void setUpTest() {
        assertNotNull(personRepository);
        assertNotNull(personService);
    }
}
