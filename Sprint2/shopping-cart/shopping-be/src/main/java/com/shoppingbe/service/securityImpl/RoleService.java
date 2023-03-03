package com.shoppingbe.service.securityImpl;

import com.shoppingbe.entity.Role;
import com.shoppingbe.entity.RoleName;
import com.shoppingbe.repository.account.IRoleRepository;
import com.shoppingbe.service.IRoleService;
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
