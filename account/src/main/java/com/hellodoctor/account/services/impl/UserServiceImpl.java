package com.hellodoctor.account.services.impl;

import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.repositories.PatientProfileRepository;
import com.hellodoctor.account.repositories.RoleRepository;
import com.hellodoctor.account.repositories.UserRepository;
import com.hellodoctor.account.services.UserService;
import com.hellodoctor.common.entities.Role;
import com.hellodoctor.common.entities.User;
import com.hellodoctor.common.entities.UserProfile;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import com.hellodoctor.common.models.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Khoa
 * @created 4/19/2019
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    @Autowired
    private Converter userDtoToUser;

    @Autowired
    private Converter patientToPatientDto;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO getInfoUserLogged() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (o instanceof UserDTO) {
            return (UserDTO) o;
        }
        return null;
    }

    @Override
    public PatientProfileDTO savePatientUser(UserDTO userDTO) throws ApiRuntimeException {
        User savedUser = this.saveUser(userDTO);
        UserProfile profile = new UserProfile();
        profile.setUser(savedUser);
        UserProfile savedProfile = patientProfileRepository.save(profile);
        modelMapper.addConverter(patientToPatientDto);
        PatientProfileDTO patientProfileDTO = modelMapper.map(savedProfile, PatientProfileDTO.class);
        return patientProfileDTO;
    }


    @Override
    public User saveUser(UserDTO userDTO) {
        if (userRepository.countAllByEmail(userDTO.getEmail()) > 0) {
            log.error("Error while save data: {}", "Email is already exist!");
            throw new ApiRuntimeException("Email is already exist!");
        }
        modelMapper.addConverter(userDtoToUser);
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (String role : userDTO.getRoles()) {
            Optional<Role> roleOptional = roleRepository.findRoleByName(role);
            if (roleOptional.isPresent()) {
                roles.add(roleOptional.get());
            }
        }
        user.setRoles(roles);
        User savedUer = userRepository.save(user);
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(savedUer);
        patientProfileRepository.save(userProfile);
        return savedUer;
    }


    @Override
    public User getUserByUserId(Long id) throws ApiRuntimeException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            log.error("{}: There is no User to get, check Id parameter!", this.getClass());
            throw new ApiRuntimeException("There is no User to get, check Id parameter!");
        }
        return user.get();
    }

}
