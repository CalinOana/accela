package com.accela.web;

import com.accela.api.generated.controllers.AddressesApi;
import com.accela.service.AddressesService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class AddressesController implements AddressesApi {
    private final AddressesService addressesService;

    public  ResponseEntity<Void> deleteAddress(@ApiParam(value = "ID of the Address to delete",required=true) @PathVariable("id") UUID id) {
        addressesService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
