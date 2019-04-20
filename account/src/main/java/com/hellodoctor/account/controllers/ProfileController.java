package com.hellodoctor.account.controllers;

import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.services.ProfileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return profileService.getPatientProfile(id);
    }

    @PostMapping(value = "/patient")
    @ApiOperation(value = "Create or Update Patient Profile",
            notes = "When update, have to provide userId and profile Id"
    )
    public PatientProfileDTO savePatientProfile(@RequestBody PatientProfileDTO patientProfileDTO) {
        return profileService.savePatientProfile(patientProfileDTO);
    }
}
