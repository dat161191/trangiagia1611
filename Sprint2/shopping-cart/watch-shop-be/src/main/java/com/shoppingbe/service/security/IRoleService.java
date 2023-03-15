package com.shoppingbe.service.security;

import com.shoppingbe.entity.Role;
import com.shoppingbe.entity.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

}
