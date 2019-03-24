package com.hellodoctor.auth.controllers;

import com.hellodoctor.auth.services.UserService;
import com.hellodoctor.common.models.vo.SnsUser;
import com.hellodoctor.common.models.vo.HDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login/fa", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public HDToken faceLogin(@RequestBody SnsUser user) {
        return userService.faceLogin(user);
    }

}
