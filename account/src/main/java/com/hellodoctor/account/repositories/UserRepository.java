package com.hellodoctor.account.repositories;

import com.hellodoctor.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Khoa
 * @created 4/19/2019
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Long countAllByEmail(String email);
}
