package com.hellodoctor.auth2.repositories;

import com.hellodoctor.auth2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Khoa
 * @created 4/21/2019
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String userName, String passWord);
}
