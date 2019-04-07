package com.hellodoctor.common.models.location;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 4/7/2019
 */

public class DistanceResponse {
    private String originAddress = "";
    private List<String> destinationAddresses = new ArrayList<>();
    private List<Float> distances = new ArrayList<>();

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    public void setDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public List<Float> getDistances() {
        return distances;
    }

    public void setDistances(List<Float> distances) {
        this.distances = distances;
    }
}
