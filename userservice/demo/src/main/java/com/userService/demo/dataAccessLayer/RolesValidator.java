package com.userService.demo.dataAccessLayer;

import com.userService.demo.model.Role;
import com.userService.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RolesValidator {
    @Autowired
    private RoleRepository roleRepository;

    public boolean validate(String roleName){
        Optional<Role> optional = roleRepository.findRoleByName(roleName);
        return optional.isEmpty();
    }
}
