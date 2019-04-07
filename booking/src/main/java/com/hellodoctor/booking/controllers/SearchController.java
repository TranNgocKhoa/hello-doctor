package com.hellodoctor.booking.controllers;

import com.hellodoctor.booking.entities.Department;
import com.hellodoctor.booking.models.DoctorResultDTO;
import com.hellodoctor.booking.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 4/2/2019
 */

@RestController("/booking/search")
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<DoctorResultDTO> searchDoctor(@RequestParam(name = "symptom") String symptom,
                                              @RequestParam(name = "lat") float lat,
                                              @RequestParam(name = "lng") float lng,
                                              @RequestParam(name = "dateStart") String dateStart,
                                              @RequestParam(name = "dateEnd") String dateEnd) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateStartDt = df.parse(dateStart);
            Date dateEndDt = df.parse(dateEnd);
            return doctorService.searchDoctors(symptom, lat, lng, dateStartDt, dateEndDt);
        }
        catch (ParseException e) {
           throw new RuntimeException();
        }

    }
}
