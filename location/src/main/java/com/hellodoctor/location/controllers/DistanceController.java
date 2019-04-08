package com.hellodoctor.location.controllers;

import com.hellodoctor.common.models.location.DistanceRequest;
import com.hellodoctor.common.models.location.DistanceResponse;
import com.hellodoctor.location.services.GoogleMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khoa
 * @created 4/7/2019
 */
@RestController("/distance")
public class DistanceController {

    @Autowired
    private GoogleMatrixService googleMatrixService;

    @PostMapping
    public DistanceResponse calculateDistanceList(@RequestBody DistanceRequest distanceRequest) {
        //TODO: call Google API to get List Distance
        return googleMatrixService.getListDistance(distanceRequest);
    }
}
