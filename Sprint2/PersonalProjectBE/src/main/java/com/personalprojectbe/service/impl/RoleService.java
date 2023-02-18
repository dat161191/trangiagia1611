package com.personalprojectbe.service.impl;

import com.personalprojectbe.model.Role;
import com.personalprojectbe.model.RoleName;
import com.personalprojectbe.repository.account.ICustomerrRpository;
import com.personalprojectbe.repository.account.IRoleRepository;
import com.personalprojectbe.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
