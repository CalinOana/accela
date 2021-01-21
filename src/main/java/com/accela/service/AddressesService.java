package com.accela.service;

import com.accela.api.generated.models.AddressDTO;
import com.accela.components.cast.ModelMapperExtended;
import com.accela.model.Address;
import com.accela.repository.AddressRepository;
import com.accela.validator.AddressValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressesService {
    private final AddressRepository addressRepository;
    private final AddressValidator addressValidator;
    private final ModelMapperExtended modelMapperExtended;

    public void deleteAddress(UUID id) {
        addressValidator.validateAddressBeforeDelete(id);
        addressRepository.delete(addressRepository.findById(id).get());
    }

    public AddressDTO editAddress(AddressDTO addressDTO) {
        addressValidator.validateAddressBeforeEdit(addressDTO);
        final Address address = addressRepository.findById(addressDTO.getId()).get();
        copyAddressFields(address, addressDTO);
        addressRepository.save(address);
        return modelMapperExtended.map(address, AddressDTO.class);
    }

    private void copyAddressFields(Address address, AddressDTO addressDTO) {
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
    }
}
