package com.shoppingbe.service;

import com.shoppingbe.entity.Role;
import com.shoppingbe.entity.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

}
