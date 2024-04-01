package com.userService.demo.controller;

import com.userService.demo.dto.RoleDtos.RoleRequestDto;
import com.userService.demo.dto.RoleDtos.RoleResponseDto;
import com.userService.demo.exception.RoleNotFoundException;
import com.userService.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    @Autowired
    private RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public RoleResponseDto createRole( @RequestBody RoleRequestDto roleRequestDto){
                return roleService.createRole(roleRequestDto);
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

}
