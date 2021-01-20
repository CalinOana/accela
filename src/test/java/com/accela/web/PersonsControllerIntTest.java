package com.accela.web;

import com.accela.BaseServiceIntTest;
import com.accela.DataMock;
import com.accela.api.generated.models.AddressDTO;
import com.accela.api.generated.models.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonsControllerIntTest extends BaseServiceIntTest {

    private static final String PERSONS_API_BASE_PATH = "/persons";

    @BeforeEach
    void setup() {
        final PersonsController personsController = new PersonsController(personService);
        mockMvc = MockMvcBuilders.standaloneSetup(personsController).build();
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Given request to Persons controller assert that status is ok and response is correct")
    @Transactional
    @Rollback
    void personsGetTest() throws Exception {
        personRepository.save(DataMock.mockValidPersonWithAddresses());
        final MvcResult mvcResult = mockMvc.perform(get(PERSONS_API_BASE_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        ObjectMapper objectMapper=new ObjectMapper();
        List<PersonDTO> persons= objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, PersonDTO.class));
        assertEquals(1,persons.size());
        final PersonDTO personDTO = persons.get(0);
        assertEquals("John", personDTO.getFirstName());
        final List<AddressDTO> addresses = personDTO.getAddresses();
        assertEquals(2, addresses.size());
    }
}
