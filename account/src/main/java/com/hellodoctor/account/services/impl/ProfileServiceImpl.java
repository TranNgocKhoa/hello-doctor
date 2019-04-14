package com.hellodoctor.account.services.impl;

import com.hellodoctor.account.entities.UserProfile;
import com.hellodoctor.account.models.CommentDTO;
import com.hellodoctor.account.models.DoctorProfileDTO;
import com.hellodoctor.account.models.PatientProfileDTO;
import com.hellodoctor.account.repositories.DoctorProfileRepository;
import com.hellodoctor.account.repositories.PatientProfileRepository;
import com.hellodoctor.account.services.ProfileService;
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
    private ModelMapper modelMapper;
    @Autowired
    private Converter patientToPatientDto;
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

    private List<CommentDTO> getListCommentByDoctor(Long doctorId) {
        return doctorProfileRepository.getCommentByDoctorId(doctorId);
    }
}
