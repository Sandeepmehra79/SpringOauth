package com.userService.demo.service;

import com.userService.demo.exception.RoleNotFoundException;
import com.userService.demo.mapper.RoleToRoleResponseDto;
import com.userService.demo.dto.RoleDtos.RoleResponseDto;
import com.userService.demo.dto.RoleDtos.RoleRequestDto;
import com.userService.demo.model.Role;
import com.userService.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto){
        Role role = new Role();
        role.setName(roleRequestDto.getName());
        // saving and getting the new RoleResponseDto from User object
        return RoleToRoleResponseDto.convert(roleRepository.save(role));
    }

    public boolean validateRole(String roleName) throws RoleNotFoundException {
        Optional<Role> optional = roleRepository.findRoleByName(roleName);
        return optional.isPresent();
    }

    public Role[] getAllRoles(){
        Role[] roles = roleRepository.findAll().toArray(new Role[0]);
        return roles;
    }
}
