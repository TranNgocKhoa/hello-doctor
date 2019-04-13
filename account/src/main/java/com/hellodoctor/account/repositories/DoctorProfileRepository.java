package com.hellodoctor.account.repositories;

import com.hellodoctor.account.entities.DoctorProfile;
import com.hellodoctor.account.models.CommentDTO;
import com.hellodoctor.account.models.DoctorProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Khoa
 * @created 4/13/2019
 */
@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {
    @Query(nativeQuery = true, name = "getDoctorProfileDTO")
    DoctorProfileDTO getDoctorProfileDTO(@Param("doctorId") Long doctorId);

    @Query(nativeQuery = true, name = "getCommentByDoctorId")
    List<CommentDTO> getCommentByDoctorId(@Param("doctorId") Long doctorId);
}


