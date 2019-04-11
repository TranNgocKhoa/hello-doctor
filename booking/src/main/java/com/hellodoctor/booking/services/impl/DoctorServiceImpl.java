package com.hellodoctor.booking.services.impl;

import com.hellodoctor.booking.entities.DoctorProfile;
import com.hellodoctor.booking.models.DoctorResultDTO;
import com.hellodoctor.booking.repositories.DoctorProfileRepository;
import com.hellodoctor.booking.services.DoctorService;
import com.hellodoctor.booking.services.LocationService;
import com.hellodoctor.common.constants.PartOfDay;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Khoa
 * @created 4/2/2019
 */
@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorProfileRepository doctorProfileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LocationService locationService;

    @Override
    public List<DoctorResultDTO> searchDoctors(String symptom, float lat, float lng, String partOfDay) {
        List<DoctorResultDTO> data = null;
        try {
            data =  getListDoctorBySymptomAndTime(symptom, partOfDay);
            log.info("========== DATABASE  ===============");
            String origin = new StringBuilder().append(lat).append(",").append(lng).toString();
            List<String> destinations = generateDestinations(data);

            List<Long> distances = locationService.getListDistancesFromOneOrigin(origin, destinations).getDistances();
            log.info("========== GOOGLE MAP ===============");
            if(data.size() == distances.size()) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setDistance(distances.get(i)/1000.0f);
                    data.get(i).setScore(data.get(i).getScore() - (distances.get(i)/1000.0f));
                }
            }
            data.sort((p1, p2) -> (-1)*Float.compare(p1.getScore(), p2.getScore()));
        } catch (ApiRuntimeException ex) {
            log.error("Error when search at Service {}", ex.getMessage());
        }
        log.info("========== DATA ===============");
        log.info(data.toString());
        return data;

    }

    private List<DoctorResultDTO> getListDoctorBySymptomAndTime(String symptom, String partOfDay) {
        PartOfDay part = PartOfDay.getPartOfDayByString(partOfDay);
        return doctorProfileRepository.getListDoctorBySymptomAndTime(symptom, part.getStartHour(), part.getEndHour());
    }

    private List<String> generateDestinations(List<DoctorResultDTO> dataDoctors) {
        return dataDoctors.stream()
                .map(doctorResultDTO -> doctorResultDTO.getAddress())
                .collect(Collectors.toList());
    }

}

