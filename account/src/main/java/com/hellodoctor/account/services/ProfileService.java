package com.hellodoctor.account.services;

import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.account.models.PatientProfileDTO;

/**
 * @author Khoa
 * @created 4/13/2019
 */
public interface ProfileService {
    DoctorProfileDTO getDoctorProfile(String id);
    PatientProfileDTO getPatientProfile(String id);
}
