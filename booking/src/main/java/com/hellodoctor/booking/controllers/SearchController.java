package com.hellodoctor.booking.controllers;

import com.hellodoctor.booking.entities.Department;
import com.hellodoctor.booking.models.DoctorResultDTO;
import com.hellodoctor.booking.services.DoctorService;
import com.hellodoctor.booking.services.LocationService;
import com.hellodoctor.common.constants.PartOfDay;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.location.DistanceResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SearchController {


    @Autowired
    private DoctorService doctorService;


    @GetMapping(params = {"symptom", "lat", "lng", "partOfDay"})
    @ApiOperation(value = "Search Doctor By With Coordinate")
    public List<DoctorResultDTO> searchDoctor(@RequestParam(name = "symptom") String symptom,
                                              @RequestParam(name = "lat") float lat,
                                              @RequestParam(name = "lng") float lng,
                                              @RequestParam(name = "partOfDay") String partOfDay) {
        List<DoctorResultDTO> data = null;
        log.info("=====START SEARCH");
        data = doctorService.searchDoctors(symptom, lat, lng, partOfDay);
        log.info("=====END SEARCH");
        return data;
    }

    @GetMapping(params = {"symptom", "address", "partOfDay"})
    @ApiOperation(value = "Search Doctor By With Address")
    public List<DoctorResultDTO> searchDoctor(@RequestParam(name = "symptom") String symptom,
                                              @RequestParam(name = "address") String address,
                                              @RequestParam(name = "partOfDay") String partOfDay) {
        List<DoctorResultDTO> data = null;
        log.info("=====START SEARCH");
        data = doctorService.searchDoctors(symptom, address, partOfDay);
        log.info("=====END SEARCH");
        return data;
    }


}
