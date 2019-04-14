package com.hellodoctor.account.repositories;

import com.hellodoctor.account.entities.UserProfile;
import com.hellodoctor.account.models.PatientProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Khoa
 * @created 4/14/2019
 */
public interface PatientProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile getUserProfileById(Long id);
}
