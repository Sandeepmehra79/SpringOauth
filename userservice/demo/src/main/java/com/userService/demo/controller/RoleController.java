package com.userService.demo.controller;

import com.userService.demo.dto.RoleDtos.RoleRequestDto;
import com.userService.demo.dto.RoleDtos.RoleResponseDto;
import com.userService.demo.exception.RoleNotFoundException;
import com.userService.demo.model.Role;
import com.userService.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto){
                RoleResponseDto role =  roleService.createRole(roleRequestDto);
                return ResponseEntity.ok(role);
    }
    @GetMapping("home")
    public String homePage(){
        return "Hello roles home page";
    }

    @PostMapping("validateRole")
    public String validateRole(@RequestBody RoleRequestDto requestDto) throws RoleNotFoundException {
        if (roleService.validateRole(requestDto.getName())){
            return "Role exist";
        }
        return "Role don't exist";
    }
    @GetMapping("")
    public Role[] getAllRoles(){
       return  roleService.getAllRoles();
    }

}
