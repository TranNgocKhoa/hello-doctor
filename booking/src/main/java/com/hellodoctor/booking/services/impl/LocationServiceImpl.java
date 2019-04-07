package com.hellodoctor.booking.services.impl;

import com.hellodoctor.booking.services.LocationService;
import com.hellodoctor.common.models.location.DistanceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Khoa
 * @created 4/7/2019
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Override
    public DistanceResponse getListDistancesFromOneOrigin(String origin, List<String> destinations) {
        return null;
    }
}
