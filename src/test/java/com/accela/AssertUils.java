package com.accela;

import com.accela.model.Address;
import com.accela.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class AssertUils {
    public static List<Address> filterAddressList(Person person, String city, String street, String state, String postalCode) {
        return person.getAddresses().stream().filter(address -> {
            boolean isElementAPass = city.equals(address.getCity());
            isElementAPass &= street.equals(address.getStreet());
            isElementAPass &= state.equals(address.getState());
            isElementAPass &= postalCode.equals(address.getPostalCode());
            isElementAPass &= person.equals(address.getPerson());
            return isElementAPass;
        }).collect(Collectors.toList());
    }
}
