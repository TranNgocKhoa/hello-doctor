package com.hellodoctor.account.config;

import com.hellodoctor.account.entities.UserProfile;
import com.hellodoctor.account.models.PatientProfileDTO;
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
}
