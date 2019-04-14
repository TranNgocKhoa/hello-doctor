package com.hellodoctor.location.controllers;

import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.location.models.Address;
import com.hellodoctor.location.models.AddressAC;
import com.hellodoctor.location.services.GooglePlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 4/14/2019
 */
@RestController
@RequestMapping(value = "autoComplete")
@Api(value = "Auto complete controller")
@Slf4j
public class AutoCompleteController {

    @Autowired
    private GooglePlaceService googlePlaceService;

    @GetMapping
    @ApiOperation(value = "Get predictions from word that user input")
    public List<AddressAC> autoCompleteGoogleApi(@RequestParam(name = "word") String word) {
        List<AddressAC> data = null;
        try {
            data = googlePlaceService.getPredictAddress(word);
        } catch (ApiRuntimeException ex) {
            log.error("Error in {}", ex.getMessage());
            data = new ArrayList<>();
        }
        return data;
    }
}
