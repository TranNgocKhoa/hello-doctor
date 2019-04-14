package com.hellodoctor.location.utils;

import com.hellodoctor.common.constants.GoogleMapConstant;
import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ApiRuntimeException;

import java.util.List;

/**
 * @author Khoa
 * @created 3/31/2019
 */
public class GMapUtil {

    public static String buildReverseGeocoding(float lat, float lng) {
        try {
            return GoogleMapConstant.URL_GEOCODING
                    + GoogleMapConstant.JSON_OUTPUT
                    + "?latlng="
                    + lat
                    + ","
                    + lng
                    + "&key="
                    + GoogleMapConstant.API_KEY;
        } catch (Exception e) {
            throw new ApiRuntimeException(ReturnCode.ARGS_NOT_VALID, e.getMessage());
        }
    }

    public static String buildGeocoding(String address) {
        StringBuilder url = new StringBuilder()
                .append(GoogleMapConstant.URL_GEOCODING)
                .append(GoogleMapConstant.JSON_OUTPUT)
                .append("?address=")
                .append(address)
                .append("&key=")
                .append(GoogleMapConstant.API_KEY);
        return url.toString();
    }

    public static String buildDistance(String origin, List<String> destinations) {
        StringBuilder destinationsParams = new StringBuilder();
        destinations.forEach(des -> {
            destinationsParams.append(des);
            destinationsParams.append("|");
        });
        StringBuilder url = new StringBuilder()
                .append(GoogleMapConstant.URL_DISTANCE)
                .append(GoogleMapConstant.JSON_OUTPUT)
                .append("?")
                .append(GoogleMapConstant.UNIT_METRIC)
                .append("&origins=")
                .append(origin)
                .append("&destinations=")
                .append(destinationsParams)
                .append("&key=")
                .append(GoogleMapConstant.API_KEY);
        return url.toString();
    }

    public static String buildAutoCompleteUrl(String word) {
        StringBuilder url = new StringBuilder()
                .append(GoogleMapConstant.URL_PLACE)
                .append(GoogleMapConstant.AUTOCOMPLETE)
                .append("/")
                .append(GoogleMapConstant.JSON_OUTPUT)
                .append("?input=")
                .append(word)
                .append("&types=address&components=country:vn&radius=500")
                .append("&key=")
                .append(GoogleMapConstant.API_KEY);
        return url.toString();

    }
}
