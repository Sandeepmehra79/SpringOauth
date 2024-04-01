package com.userService.demo.mapper;

import com.userService.demo.dto.RoleDtos.RoleResponseDto;
import com.userService.demo.model.Role;

public class RoleToRoleResponseDto {
    public static RoleResponseDto convert(Role role){
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setName(role.getName());
        roleResponseDto.setId(role.getId());
        return roleResponseDto;
    }
}
