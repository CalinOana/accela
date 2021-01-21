package com.accela.validator;

import com.accela.api.generated.models.AddressDTO;
import com.accela.exceptions.ResourceNotFoundException;
import com.accela.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.accela.exceptions.ErrorKey.withKey;
import static com.accela.exceptions.ExceptionMessageKeyConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressValidator extends BaseValidator {
    private final AddressRepository addressRepository;

    public void validateAddressFields(AddressDTO addressDTO) {
        validateFieldNotNull(addressDTO, withKey(NULL_ADDRESS));
        validateFieldNotNull(addressDTO.getStreet(), withKey(NULL_ADDRESS_STREET));
        validateFieldNotNull(addressDTO.getCity(), withKey(NULL_ADDRESS_CITY));
        validateFieldNotNull(addressDTO.getState(), withKey(NULL_ADDRESS_STATE));
        validateFieldNotNull(addressDTO.getPostalCode(), withKey(NULL_ADDRESS_POSTAL_CODE));
    }

    public void validateAddresses(List<AddressDTO> addresses) {
        addresses.forEach(this::validateAddressFields);
    }

    public void validateAddressBeforeDelete(UUID id) {
        validateAddressExists(id);
    }

    private void validateAddressExists(UUID id) {
        validateFieldNotNull(id, withKey(NULL_ID));
        throwExceptionIfAddressNotFound(id);
    }

    private void throwExceptionIfAddressNotFound(UUID id) {
        if (addressRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException(withKey(INEXISTENT_ADDRESS));
        }
    }

    public void validateAddressBeforeEdit(AddressDTO addressDTO) {
        validateAddressFields(addressDTO);
    }
}
