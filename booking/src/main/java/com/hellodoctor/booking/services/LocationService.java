package com.hellodoctor.booking.services;


import com.hellodoctor.common.models.location.DistanceResponse;

import java.util.List;

/**
 * @author Khoa
 * @created 4/7/2019
 */
public interface LocationService {
    DistanceResponse getListDistancesFromOneOrigin(String origin, List<String> destinations);
}
