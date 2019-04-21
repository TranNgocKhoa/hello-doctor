package com.hellodoctor.account.services;

import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.common.entities.User;
import com.hellodoctor.common.entities.UserProfile;
import com.hellodoctor.common.models.user.UserDTO;

/**
 * @author Khoa
 * @created 4/19/2019
 */
public interface UserService {
    UserDTO getInfoUserLogged();
    PatientProfileDTO savePatientUser(UserDTO userDTO);
    User saveUser(UserDTO userDTO);
    User getUserByUserId(Long id);
}
