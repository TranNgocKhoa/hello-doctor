package com.hellodoctor.booking.services;


import com.hellodoctor.booking.models.DoctorResultDTO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * @author Khoa
 * @created 4/2/2019
 */
public interface DoctorService {

    List<DoctorResultDTO> searchDoctors(String symptom, float lat, float lng, String partOfDay);
    List<DoctorResultDTO> searchDoctors(String symptom, String address, String partOfDay);
}
