package com.accela.validator;

import com.accela.api.generated.models.PersonDTO;
import com.accela.exceptions.ResourceNotFoundException;
import com.accela.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.accela.exceptions.ErrorKey.withKey;
import static com.accela.exceptions.ExceptionMessageKeyConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonValidator extends BaseValidator {
    private final PersonRepository personRepository;

    public void validatePersonOnCreation(PersonDTO personDTO) {
        validateFieldNotNull(personDTO, withKey(NULL_PERSON));
        validateFieldNotNull(personDTO.getFirstName(), withKey(NULL_PERSON_FIRST_NAME));
        validateFieldNotNull(personDTO.getLastName(), withKey(NULL_PERSON_LAST_NAME));
    }

    public void validatePersonOnEdit(PersonDTO personDTO) {
        validateFieldNotNull(personDTO, withKey(NULL_PERSON));
        validateFieldNotNull(personDTO.getId(), withKey(NULL_ID));
        if (personRepository.findById(personDTO.getId()).isEmpty()) {
            throw new ResourceNotFoundException(withKey(INEXISTENT_PERSON));
        }
    }
}