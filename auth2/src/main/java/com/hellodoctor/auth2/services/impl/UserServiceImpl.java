package com.hellodoctor.auth2.services.impl;

import com.hellodoctor.auth2.entities.User;
import com.hellodoctor.auth2.models.LoginDTO;
import com.hellodoctor.auth2.models.Token;
import com.hellodoctor.auth2.repositories.UserRepository;
import com.hellodoctor.auth2.services.UserService;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.user.UserDTO;
import com.hellodoctor.common.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Khoa
 * @created 4/21/2019
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public Token auth(LoginDTO login) {
        User user = null;
        try {
            user = userRepository.findByEmail(login.getEmail());
            if (!checkIfValidOldPassword(user, login.getPassword())) {
                user = null;
            }
        } catch (Exception ex) {
            log.error("Error in {} with detail: {}", this.getClass(), ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        }
        if(user == null) {
            log.error("Error in {} with detail: {}", this.getClass(), "User not found");
            throw new ApiRuntimeException("User not found!!!");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus().name());
        userDTO.setRoles(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));
        return generateJwt(userDTO);
    }

    private Token generateJwt(UserDTO user) {
        String jwt = jwtUtils.encode(user);
//        String encoded = Base64.getEncoder().encodeToString(jwt.getBytes());

        log.info("Token: {}", jwt);

        Token token = new Token(jwt);
        return token;
    }

    private boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
