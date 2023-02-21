package com.personalprojectbe.repository.account;

import com.personalprojectbe.entity.Role;
import com.personalprojectbe.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
