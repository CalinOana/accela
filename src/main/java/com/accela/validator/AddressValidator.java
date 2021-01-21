package com.accela.validator;

import com.accela.api.generated.models.AddressDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.accela.exceptions.ErrorKey.withKey;
import static com.accela.exceptions.ExceptionMessageKeyConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressValidator extends BaseValidator {
    public void validateAddressFields(AddressDTO addressDTO) {
        validateFieldNotNull(addressDTO,withKey(NULL_ADDRESS));
        validateFieldNotNull(addressDTO.getStreet(),withKey(NULL_ADDRESS_STREET));
        validateFieldNotNull(addressDTO.getCity(),withKey(NULL_ADDRESS_CITY));
        validateFieldNotNull(addressDTO.getState(),withKey(NULL_ADDRESS_STATE));
        validateFieldNotNull(addressDTO.getPostalCode(),withKey(NULL_ADDRESS_POSTAL_CODE));
    }

    public void validateAddresses(List<AddressDTO> addresses) {
        addresses.forEach(this::validateAddressFields);
    }
}
