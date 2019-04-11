package com.hellodoctor.location.services;

import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.location.DistanceRequest;
import com.hellodoctor.common.models.location.DistanceResponse;
import com.hellodoctor.location.models.Address;
import com.hellodoctor.location.utils.GMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Khoa
 * @created 4/7/2019
 */
@Service
@Slf4j
public class GoogleMatrixService {
    @Autowired
    private RestTemplate restTemplate;

    public DistanceResponse getListDistance(DistanceRequest distanceRequest) {
        log.info("======== SIZE " + distanceRequest.getDestinations().size() + " =============================");
        String url = GMapUtil.buildDistance(distanceRequest.getOrigin(), distanceRequest.getDestinations());
        log.info("======== URL = {}", url);
        String resultApi = restTemplate.getForEntity(url, String.class).getBody();
        JSONObject parse = new JSONObject(resultApi);
        log.info("======== REST TE =============================");
        String status = parse.getString("status");
        log.info("======== " +status+" =============================");
        if(!status.equals("OK")) {
            throw new ApiRuntimeException("STATUS is " + status + ". Because isn't OK then exception.");
        }
        log.info("======== STATUS =============================");
        DistanceResponse response = new DistanceResponse();
        JSONArray origins = parse.getJSONArray("origin_addresses");
        origins.forEach(o -> {
            response.setOriginAddress((String) o);
        });
        JSONArray destinatons = parse.getJSONArray("destination_addresses");
        destinatons.forEach(o -> {
            response.getDestinationAddresses().add((String) o);
        });

        JSONArray elements = ((JSONObject) parse.getJSONArray("rows").get(0)).getJSONArray("elements");
        elements.forEach(d -> {

            JSONObject distance = (JSONObject) ((JSONObject) d).get("distance");
            response.getDistances().add(Long.valueOf(distance.get("value").toString()));
        });
        log.info("=====END=============");
        return response;
    }
}
