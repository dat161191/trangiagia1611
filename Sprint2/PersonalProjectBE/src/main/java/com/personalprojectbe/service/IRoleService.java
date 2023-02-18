package com.personalprojectbe.service;

import com.personalprojectbe.model.Role;
import com.personalprojectbe.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

}
