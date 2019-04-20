package com.hellodoctor.account.services;

import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.models.UserDTO;
import com.hellodoctor.common.entities.User;
import com.hellodoctor.common.entities.UserProfile;

/**
 * @author Khoa
 * @created 4/19/2019
 */
public interface UserService {
    PatientProfileDTO savePatientUser(UserDTO userDTO);
    User saveUser(UserDTO userDTO);
    User getUserByUserId(Long id);
}
