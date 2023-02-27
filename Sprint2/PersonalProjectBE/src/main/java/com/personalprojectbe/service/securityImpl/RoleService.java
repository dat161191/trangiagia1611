package com.personalprojectbe.service.securityImpl;

import com.personalprojectbe.entity.Role;
import com.personalprojectbe.entity.RoleName;
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
