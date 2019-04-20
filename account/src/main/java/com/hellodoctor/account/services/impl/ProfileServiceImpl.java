package com.hellodoctor.account.services.impl;

import com.hellodoctor.account.models.CommentDTO;
import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.models.UserDTO;
import com.hellodoctor.account.repositories.DoctorProfileRepository;
import com.hellodoctor.account.repositories.PatientProfileRepository;
import com.hellodoctor.account.services.ProfileService;
import com.hellodoctor.common.entities.User;
import com.hellodoctor.common.entities.UserProfile;
import com.hellodoctor.common.exceptions.ApiRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Khoa
 * @created 4/13/2019
 */
@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private DoctorProfileRepository doctorProfileRepository;
    @Autowired
    private PatientProfileRepository patientProfileRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Converter patientToPatientDto;
    @Autowired
    private Converter patientDtoToPatient;

    @Override
    public DoctorProfileDTO getDoctorProfile(String id) {
        Long doctorId = null;
        try {
            doctorId = Long.valueOf(id);
        } catch (NumberFormatException ex) {
            log.error("Error in {} while {}", this.getClass(), ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        }
        DoctorProfileDTO doctorProfileDTO = doctorProfileRepository.getDoctorProfileDTO(doctorId);
        doctorProfileDTO.setComments(this.getListCommentByDoctor(doctorId));
        return doctorProfileDTO;
    }

    @Override
    public PatientProfileDTO getPatientProfile(String id) {
        Long patientId = null;
        try {
            patientId = Long.valueOf(id);
        } catch (NumberFormatException ex) {
            log.error("Error in {} while {}", this.getClass(), ex.getMessage());
            throw new ApiRuntimeException(ex.getMessage());
        }
        UserProfile profile = patientProfileRepository.getUserProfileById(patientId);
        modelMapper.addConverter(patientToPatientDto);
        return modelMapper.map(profile, PatientProfileDTO.class);

    }

    @Override
    public PatientProfileDTO savePatientProfile(PatientProfileDTO patientProfileDTO) {
        if (patientProfileDTO == null) {
            log.error("Error in {}: patientProfileDTO is null", this.getClass());
            throw new ApiRuntimeException("patientProfileDTO is null");
        }
        if (patientProfileDTO.getPatientId() == null || patientProfileDTO.getPatientId() == 0) {
            User user = userService.saveUser(new UserDTO());
            return this.convertAndSavePatientProfile(patientProfileDTO, user);
        } else {
            User user = userService.getUserByUserId(patientProfileDTO.getUserId());
            return this.convertAndSavePatientProfile(patientProfileDTO, user);
        }
    }


    private List<CommentDTO> getListCommentByDoctor(Long doctorId) {
        return doctorProfileRepository.getCommentByDoctorId(doctorId);
    }

    private PatientProfileDTO convertAndSavePatientProfile(PatientProfileDTO patientProfileDTO,
                                                           User user) throws ApiRuntimeException {
        modelMapper.addConverter(patientDtoToPatient);
        UserProfile userProfile = modelMapper.map(patientProfileDTO, UserProfile.class);
        userProfile.setUser(user);
        UserProfile savedPatient = patientProfileRepository.save(userProfile);
        modelMapper.addConverter(patientToPatientDto);
        return modelMapper.map(savedPatient, PatientProfileDTO.class);
    }
}
