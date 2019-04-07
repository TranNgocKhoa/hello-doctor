package com.hellodoctor.common.models.location;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 4/7/2019
 */
public class DistanceRequest {
    private String origin;
    private List<String> destinations = new ArrayList<>();

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }
}
