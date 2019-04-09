package com.hellodoctor.booking.controllers;

import com.hellodoctor.booking.entities.Department;
import com.hellodoctor.booking.models.DoctorResultDTO;
import com.hellodoctor.booking.services.DoctorService;
import com.hellodoctor.booking.services.LocationService;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.location.DistanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Khoa
 * @created 4/2/2019
 */

@RestController
@RequestMapping("/search")
public class SearchController {


    @Autowired
    private DoctorService doctorService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<DoctorResultDTO> searchDoctor(@RequestParam(name = "symptom") String symptom,
                                              @RequestParam(name = "lat") float lat,
                                              @RequestParam(name = "lng") float lng,
                                              @RequestParam(name = "dateStart") String dateStart,
                                              @RequestParam(name = "dateEnd") String dateEnd) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<DoctorResultDTO> data = null;

        try {
            Date dateStartDt = df.parse(dateStart);
            Date dateEndDt = df.parse(dateEnd);
            data = doctorService.searchDoctors(symptom, lat, lng, dateStartDt, dateEndDt);
            String origin = new StringBuilder().append(lat).append(",").append(lng).toString();
            List<String> destinations = generateDestinations(data);
            List<Long> distances = locationService.getListDistancesFromOneOrigin(origin, destinations).getDistances();
            if(data.size() == distances.size()) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setDistance(distances.get(i)/1000.0f);
                }
            }
        }
        catch (ParseException e) {

        }
        catch (ApiRuntimeException ex) {

        }
        return data;
    }

    private List<String> generateDestinations(List<DoctorResultDTO> dataDoctors) {
        return dataDoctors.stream()
                .map(doctorResultDTO -> doctorResultDTO.getAddress())
                .collect(Collectors.toList());
    }
}
