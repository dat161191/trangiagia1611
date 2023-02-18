package com.personalprojectbe.repository.account;

import com.personalprojectbe.model.Role;
import com.personalprojectbe.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
