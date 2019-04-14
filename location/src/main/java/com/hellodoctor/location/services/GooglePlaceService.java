package com.hellodoctor.location.services;

import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.location.models.Address;
import com.hellodoctor.location.models.AddressAC;
import com.hellodoctor.location.utils.GMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 4/14/2019
 */
@Service
@Slf4j
public class GooglePlaceService {
    @Autowired
    private RestTemplate restTemplate;

    public List<AddressAC> getPredictAddress(String word) throws ApiRuntimeException{
        String url = GMapUtil.buildAutoCompleteUrl(word);
        List<AddressAC> predictions = null;
        try {
            String resultApi = restTemplate.getForEntity(url, String.class).getBody();
            predictions = this.parseJsonToPredictList(resultApi);
        } catch (RestClientException ex) {
            log.error("Error on query Google API with detail {}", ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        } catch (ApiRuntimeException ex) {
            throw ex;
        }
        return predictions;
    }

    private List<AddressAC> parseJsonToPredictList(String json) {
        JSONObject parse = null;
        List<AddressAC> data = new ArrayList<>();
        try {
            parse = new JSONObject(json);
            String status = parse.getString("status");
            if(!status.equals("OK")) {
                return data;
            }
            JSONArray jsonResults = parse.getJSONArray("predictions");

            jsonResults.forEach(p -> {
                JSONObject prediction = (JSONObject) p;
                String address = prediction.getString("description");
                JSONObject structuredFormatting = prediction.getJSONObject("structured_formatting");
                String mainText = structuredFormatting.getString("main_text");
                data.add(new AddressAC(address, mainText));
            });
        } catch (Exception ex) {
            log.error("Error on parse JSON result with detail {}", ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        }
        return data;
    }
}
