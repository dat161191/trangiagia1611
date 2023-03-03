package com.shoppingbe.repository.account;

import com.shoppingbe.entity.Role;
import com.shoppingbe.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
