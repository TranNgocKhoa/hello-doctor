package com.hellodoctor.booking.services.impl;

import com.hellodoctor.booking.entities.DoctorProfile;
import com.hellodoctor.booking.models.DoctorResultDTO;
import com.hellodoctor.booking.repositories.DoctorProfileRepository;
import com.hellodoctor.booking.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 4/2/2019
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorProfileRepository doctorProfileRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<DoctorResultDTO> searchDoctors(String symptom, float lat, float lng, Date start, Date end) {
        return getListDoctorBySymptomAndTime(symptom, start, end);
    }

    private List<DoctorResultDTO> getListDoctorBySymptomAndTime(String symptom, Date start, Date end) {
        return doctorProfileRepository.getListDoctorBySymptomAndTime(symptom, start, end);
    }

}

