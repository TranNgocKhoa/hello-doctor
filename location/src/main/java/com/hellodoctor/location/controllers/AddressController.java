package com.hellodoctor.location.controllers;

import com.hellodoctor.location.models.Address;
import com.hellodoctor.location.models.Coordinate;
import com.hellodoctor.location.services.GooleGeocodingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Khoa
 * @created 3/31/2019
 */

@RestController
@RequestMapping(value = "/geocoding")
@Api(value = "Account controller")
public class AddressController {

    @Autowired
    private GooleGeocodingService geocodingService;

    @GetMapping(value = "/toAddress")
    @ApiOperation(value = "Get Address from coordinate")
    public Address getAddressFromCoordinate(@RequestParam(name = "lat") float lat,
                                            @RequestParam(name = "lng") float lng) {
        return geocodingService.getAddressFromLocation(lat, lng);
    }

    @GetMapping(value = "/toCoordinate")
    @ApiOperation(value = "Get Coordinate from Address")
    public Coordinate getCoordinateFromAddress(@RequestParam(name = "address") String address) {
        return geocodingService.getCoordinateFromAddress(address);
    }
}
