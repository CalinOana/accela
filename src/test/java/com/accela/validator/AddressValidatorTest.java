package com.accela.validator;

import com.accela.DataMock;
import com.accela.exceptions.InvalidInputParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {

    AddressValidator addressValidator=new AddressValidator(null);

    @Test
    @DisplayName("Given address with valid fields, assert does not throw")
    void validateAddressFieldsTestPositive() {
        assertDoesNotThrow(()->addressValidator.validateAddressFields(DataMock.mockValidAddressDTO()));
    }

    @Test
    @DisplayName("Given address with valid fields, assert does not throw")
    void validateAddressFieldsTestNegative() {
        InvalidInputParameterException invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> addressValidator.validateAddressFields(DataMock.mockValidAddressDTO().city(null)));
        assertEquals("null.address.city", invalidInputParameterException.getErrorKey().getKey());

         invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> addressValidator.validateAddressFields(DataMock.mockValidAddressDTO().street(null)));
        assertEquals("null.address.street", invalidInputParameterException.getErrorKey().getKey());

         invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> addressValidator.validateAddressFields(DataMock.mockValidAddressDTO().state(null)));
        assertEquals("null.address.state", invalidInputParameterException.getErrorKey().getKey());
         invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> addressValidator.validateAddressFields(DataMock.mockValidAddressDTO().postalCode(null)));
        assertEquals("null.address.postal.code", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> addressValidator.validateAddressFields(null));
        assertEquals("null.address", invalidInputParameterException.getErrorKey().getKey());
    }

}
