package com.hellodoctor.account.services;

import com.hellodoctor.account.models.DoctorProfileDTO;

/**
 * @author Khoa
 * @created 4/13/2019
 */
public interface ProfileService {
    public DoctorProfileDTO getDoctorProfile(String id);
}
