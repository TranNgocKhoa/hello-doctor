package com.hellodoctor.account.controllers;

import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.models.UserDTO;
import com.hellodoctor.account.services.UserService;
import com.hellodoctor.common.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khoa
 * @created 4/19/2019
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/patient")
    public PatientProfileDTO savePatientAccount(@RequestBody UserDTO userDTO) {
        return userService.savePatientUser(userDTO);
    }

    @PostMapping(value = "/register")
    public User registerUserAccount(@RequestBody final UserDTO accountDto) {
        log.debug("Registering user account with information: {}", accountDto);
        final User registered = userService.saveUser(accountDto);
        return registered;
    }
}
