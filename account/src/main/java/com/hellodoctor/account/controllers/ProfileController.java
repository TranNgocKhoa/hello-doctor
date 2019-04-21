package com.hellodoctor.account.controllers;

import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.services.ProfileService;
import com.hellodoctor.account.services.UserService;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.user.UserDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Khoa
 * @created 4/13/2019
 */

@RestController
@RequestMapping(value = "/profile")
@Slf4j
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

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

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public PatientProfileDTO getOwnerPatientProfile() {
        UserDTO userDTO = userService.getInfoUserLogged();
        if (userDTO == null) {
            throw new ApiRuntimeException("Authentication failed. Token is not correct!");
        }
        return profileService.getPatientProfile(userDTO.getId().toString());
    }
}
