package com.hellodoctor.location.services;

import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.location.models.Coordinate;
import com.hellodoctor.location.models.Address;
import com.hellodoctor.location.utils.GMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Khoa
 * @created 3/31/2019
 */
@Service
@Slf4j
public class GooleGeocodingService {
    @Autowired
    private RestTemplate restTemplate;

    public Address getAddressFromLocation(float lat, float lng) {
        String url = GMapUtil.buildReverseGeocoding(lat, lng);
        String resultApi = restTemplate.getForEntity(url, String.class).getBody();

        JSONObject parse = new JSONObject(resultApi);
        JSONArray jsonResults = parse.getJSONArray("results");
        JSONObject firstAddress = jsonResults.getJSONObject(0);
        Address resultAddress = new Address(firstAddress.get("formatted_address").toString(),
                firstAddress.get("types").toString());

        try {
            for (Object element : jsonResults
            ) {
                JSONObject result = (JSONObject) element;
                if (result.get("types").toString().contains("street_address")) {
                   resultAddress = new Address(
                           result.get("formatted_address").toString(),
                           "street_address");
                    break;
                }

            }
        }
        catch (Exception e) {
            throw  new ApiRuntimeException(ReturnCode.BAD_REQUEST, "Error when parse JSON from Google Map");
        }

        return resultAddress;
    }

    public Coordinate getCoordinateFromAddress(String address) {
        String url = GMapUtil.buildGeocoding(address);
        Coordinate coordinate = null;
        try {
            String resultApi = restTemplate.getForEntity(url, String.class).getBody();
            coordinate = this.parseJsonToCoordinate(resultApi);
        } catch (RestClientException ex) {
            log.error("Error on query Google API with detail {}", ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        } catch (ApiRuntimeException ex) {
            throw ex;
        }
        return coordinate;
    }

    private Coordinate parseJsonToCoordinate(String json) {
        Coordinate data = null;
        try {
            JSONObject parse = new JSONObject(json);
            String status = parse.getString("status");
            if(!status.equals("OK")) {
                return data;
            }
            JSONArray jsonResults = parse.getJSONArray("results");
            JSONObject location = jsonResults.getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            data = new Coordinate(location.getFloat("lat"), location.getFloat("lng"));

        } catch (Exception ex) {
            log.error("Error on parse JSON result with detail {}", ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        }
        return data;
    }

}
