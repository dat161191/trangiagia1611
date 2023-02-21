package com.personalprojectbe.service;

import com.personalprojectbe.entity.Role;
import com.personalprojectbe.entity.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

}
