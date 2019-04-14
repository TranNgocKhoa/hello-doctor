package com.hellodoctor.account.controllers;

import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khoa
 * @created 4/13/2019
 */

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/doctor/{id}")
    public DoctorProfileDTO getDoctorProfile(@PathVariable String id) {
        return profileService.getDoctorProfile(id);
    }

    @GetMapping(value = "/patient/{id}")
    public PatientProfileDTO getPatientProfile(@PathVariable String id) {
        return null;
    }


}
