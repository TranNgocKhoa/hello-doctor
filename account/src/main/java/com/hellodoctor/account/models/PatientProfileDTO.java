package com.hellodoctor.account.models;

import com.hellodoctor.common.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Khoa
 * @created 4/14/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileDTO {
    private Long userId;
    private Long patientId;
    private Gender gender;
    private String name;
    private String phoneNumber;
    private Date dateOfBirth;
    private String description;
    private String avatarImg;
    private String personIdNumber;
    private Float weight;
    private Float height;
}
