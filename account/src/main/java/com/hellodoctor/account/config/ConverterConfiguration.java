package com.hellodoctor.account.config;

import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.models.UserDTO;
import com.hellodoctor.common.entities.User;
import com.hellodoctor.common.entities.UserProfile;
import com.hellodoctor.common.entities.UserStatus;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Khoa
 * @created 4/14/2019
 */
@Configuration
public class ConverterConfiguration {
    @Bean
    public Converter<UserProfile, PatientProfileDTO> patientToPatientDto() {
        return new AbstractConverter<UserProfile, PatientProfileDTO>() {
            @Override
            protected PatientProfileDTO convert(UserProfile userProfile) {

                return new PatientProfileDTO(
                        userProfile.getUser() != null ? userProfile.getUser().getId() : 0L,
                        userProfile.getId(),
                        userProfile.getGender(),
                        userProfile.getName(),
                        userProfile.getPhoneNumber(),
                        userProfile.getDateOfBirth(),
                        userProfile.getDescription(),
                        userProfile.getAvatarImg(),
                        userProfile.getPersonIdNumber(),
                        userProfile.getWeight(),
                        userProfile.getHeight()

                );
            }
        };
    }

    @Bean
    public Converter<PatientProfileDTO, UserProfile> patientDtoToPatient() {
        return new AbstractConverter<PatientProfileDTO, UserProfile>() {
            @Override
            protected UserProfile convert(PatientProfileDTO patientProfileDTO) {
                UserProfile userProfile = new UserProfile();
                userProfile.setId(patientProfileDTO.getPatientId());
                userProfile.setGender(patientProfileDTO.getGender());
                userProfile.setName(patientProfileDTO.getName());
                userProfile.setPhoneNumber(patientProfileDTO.getPhoneNumber());
                userProfile.setDateOfBirth(patientProfileDTO.getDateOfBirth());
                userProfile.setDescription(patientProfileDTO.getDescription());
                userProfile.setAvatarImg(patientProfileDTO.getAvatarImg());
                userProfile.setPersonIdNumber(patientProfileDTO.getPersonIdNumber());
                userProfile.setWeight(patientProfileDTO.getWeight());
                userProfile.setHeight(patientProfileDTO.getHeight());
                return userProfile;
            }
        };
    }

    @Bean
    public Converter<UserDTO, User> userDtoToUser() {
        return new AbstractConverter<UserDTO, User>() {
            @Override
            protected User convert(UserDTO userDTO) {
                User user = new User();
                user.setId(userDTO.getId());
                user.setPassword(userDTO.getPassword());
                user.setEmail(userDTO.getEmail());
                user.setStatus(UserStatus.getUserStatusByString(userDTO.getStatus()));
                return user;
            }
        };
    }
}
