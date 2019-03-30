package com.hellodoctor.location.controllers;

import com.hellodoctor.location.models.Address;
import com.hellodoctor.location.services.GooleGeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khoa
 * @created 3/31/2019
 */

@RestController(value = "/geocoding/")
public class AddressController {

    @Autowired
    private GooleGeocodingService geocodingService;

    @GetMapping(name = "/toAddress")
    public Address getAddressFromCoordinate(@RequestParam(name = "lat") float lat,
                                            @RequestParam(name = "lng") float lng) {
        return geocodingService.getAddressFromLocation(lat, lng);
    }
}
