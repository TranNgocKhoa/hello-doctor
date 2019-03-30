package com.hellodoctor.location.utils;

import com.hellodoctor.common.constants.GoogleMapConstant;
import com.hellodoctor.common.constants.ReturnCode;
import com.hellodoctor.common.exceptions.ApiRuntimeException;

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
}
