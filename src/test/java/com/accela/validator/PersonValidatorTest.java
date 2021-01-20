package com.accela.validator;

import com.accela.exceptions.InvalidInputParameterException;
import com.accela.exceptions.ResourceNotFoundException;
import com.accela.model.Person;
import com.accela.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static com.accela.DataMock.mockValidPersonDTOWithAddresses;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonValidatorTest {
    @Mock
    private PersonRepository personRepositoryMock;

    private PersonValidator personValidator;

    @BeforeEach
    void setup() {
        personValidator = new PersonValidator(personRepositoryMock);
    }

    @Test
    @DisplayName("Given valid personDTO when validatePersonOnCreation assert does not fail")
    void validatePersonOnCreationTestPositive() {
        assertDoesNotThrow(() -> personValidator.validatePersonOnCreation(mockValidPersonDTOWithAddresses()));
    }

    @Test
    @DisplayName("Given invalid personDTO when validatePersonOnCreation assert throws correct exceptions")
    void validatePersonOnCreationTestNegative() {
        InvalidInputParameterException invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> personValidator.validatePersonOnCreation(null));
        assertEquals("null.person", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class, () -> personValidator.validatePersonOnCreation(mockValidPersonDTOWithAddresses().firstName(null)));
        assertEquals("null.person.first.name", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class, () -> personValidator.validatePersonOnCreation(mockValidPersonDTOWithAddresses().lastName(null)));
        assertEquals("null.person.last.name", invalidInputParameterException.getErrorKey().getKey());

    }

    @Test
    @DisplayName("Given invalid personDTO when validatePersonOnEdit assert throws correct exceptions")
    void validatePersonOnEditTestNegative() {
        InvalidInputParameterException invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> personValidator.validatePersonOnEdit(null));
        assertEquals("null.person", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class, () -> personValidator.validatePersonOnEdit(mockValidPersonDTOWithAddresses()));
        assertEquals("null.id", invalidInputParameterException.getErrorKey().getKey());

        when(personRepositoryMock.findById(any())).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> personValidator.validatePersonOnEdit(mockValidPersonDTOWithAddresses().id(UUID.randomUUID())));
        assertEquals("person.inexistent", resourceNotFoundException.getErrorKey().getKey());

    }

    @Test
    @DisplayName("Given valid personDTO when validatePersonOnEdit assert does not fail")
    void validatePersonOnEditTestPositive() {
        when(personRepositoryMock.findById(any())).thenReturn(Optional.of(new Person()));
        assertDoesNotThrow(() -> personValidator.validatePersonOnEdit(mockValidPersonDTOWithAddresses().id(UUID.randomUUID())));
    }

}
