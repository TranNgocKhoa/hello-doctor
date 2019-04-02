package com.hellodoctor.booking.services;


import com.hellodoctor.booking.models.DoctorResultDTO;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * @author Khoa
 * @created 4/2/2019
 */
public interface DoctorService {

    Page<DoctorResultDTO> searchDoctors(String symptom, float lat, float lng, Date start, Date end);
}
