package com.hellodoctor.booking.services.impl;

import com.hellodoctor.booking.services.LocationService;
import com.hellodoctor.common.models.location.DistanceRequest;
import com.hellodoctor.common.models.location.DistanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Khoa
 * @created 4/7/2019
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${location-service.url}")
    private String locationUrl;

    @Override
    public DistanceResponse getListDistancesFromOneOrigin(String origin, List<String> destinations) {
        DistanceRequest requestData = new DistanceRequest();
        requestData.setOrigin(origin);
        requestData.setDestinations(destinations);
        return restTemplate
                .postForEntity(locationUrl + "/distance", requestData, DistanceResponse.class)
                .getBody();
    }
}
