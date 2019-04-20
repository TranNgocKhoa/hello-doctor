package com.hellodoctor.auth2.controllers;

import com.hellodoctor.auth2.entities.User;
import com.hellodoctor.auth2.models.LoginDTO;
import com.hellodoctor.auth2.models.Token;
import com.hellodoctor.auth2.services.UserService;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

/**
 * @author Khoa
 * @created 4/21/2019
 */
@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody LoginDTO login) throws ApiRuntimeException {

        if (login.getEmail() == null || login.getPassword() == null) {
            throw new ApiRuntimeException("Please fill in username and password");
        }

        return userService.auth(login);
    }
}
