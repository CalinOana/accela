package com.accela.service;

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
    public void deleteAddress(UUID id) {
        addressValidator.validateAddressBeforeDelete(id);
        addressRepository.delete(addressRepository.findById(id).get());
    }
}
