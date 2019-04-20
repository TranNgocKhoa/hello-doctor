package com.hellodoctor.account.repositories;

import com.hellodoctor.common.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Khoa
 * @created 4/21/2019
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);
}
