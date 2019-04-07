package com.hellodoctor.booking.repositories;

import com.hellodoctor.booking.entities.DoctorProfile;
import com.hellodoctor.booking.models.DoctorResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;


/**
 * @author Khoa
 * @created 4/2/2019
 */
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {

    @Query(nativeQuery = true, name = "getListDoctorBySymptomAndTime")
    List<DoctorResultDTO> getListDoctorBySymptomAndTime(@Param("symptom") String symptom,
                                                        @Param("startPart") Date startPart,
                                                        @Param("endPart") Date endPart);

}
