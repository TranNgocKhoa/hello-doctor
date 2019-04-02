package com.hellodoctor.booking.services.impl;

import com.hellodoctor.booking.entities.DoctorProfile;
import com.hellodoctor.booking.models.DoctorResultDTO;
import com.hellodoctor.booking.services.DoctorService;
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
    @Override
    public Page<DoctorResultDTO> searchDoctors(String symptom, float lat, float lng, Date start, Date end) {
        List<DoctorProfile> doctorProfileList = getListDoctorBySymptomAndTime(symptom, start, end);
        return null;
    }

    private List<DoctorProfile> getListDoctorBySymptomAndTime(String symptom, Date start, Date end) {
        return null;
    }
}
