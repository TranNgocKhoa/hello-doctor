package com.hellodoctor.location.controllers;

import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.location.DistanceRequest;
import com.hellodoctor.common.models.location.DistanceResponse;
import com.hellodoctor.location.services.GoogleMatrixService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khoa
 * @created 4/7/2019
 */
@RestController
@RequestMapping(value = ("/distance"))
@Api(value = "Distance Controller")
public class DistanceController {

    @Autowired
    private GoogleMatrixService googleMatrixService;

    @PostMapping
    @ApiOperation(value = "Get distance list from 1 origin and list destination")
    public DistanceResponse calculateDistanceList(@RequestBody DistanceRequest distanceRequest) {
        //TODO: call Google API to get List Distance
        try {
            return googleMatrixService.getListDistance(distanceRequest);
        } catch (ApiRuntimeException ex) {

        }
        return new DistanceResponse();
    }
}
