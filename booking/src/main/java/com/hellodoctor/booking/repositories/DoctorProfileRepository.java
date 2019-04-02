package com.hellodoctor.booking.repositories;

import com.hellodoctor.booking.entities.DoctorProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


/**
 * @author Khoa
 * @created 4/2/2019
 */
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {

    @Query()
    List<DoctorProfile> getListDoctorBySymptomAndTime(String symptom, Date start, Date end);
}
