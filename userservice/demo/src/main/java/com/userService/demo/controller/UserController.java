package com.userService.demo.controller;

import com.userService.demo.dto.ResponseDto;
import com.userService.demo.dto.UserDto.*;
import com.userService.demo.exception.RoleNotFoundException;
import com.userService.demo.exception.UserNotFoundException;
import com.userService.demo.service.UserService;
//import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@PreAuthorize("hasAuthority('USER')")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserDetails(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }
    @PostMapping("create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) throws RoleNotFoundException {
        return userService.createUser(userRequestDto);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserResponseDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto request) {

        UserResponseDto userDto = userService.setUserRoles(userId, request.getRoleIds());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<String > getUserHome() {
        return ResponseEntity.ok("this is user home page from user controller");

    }

    @GetMapping("")
    public List<UserResponseDto> getAllUsers(){
        return userService.findAllUsers();
    }



    @PostMapping("updatePassword")
    public UserResponseDto updatePassword(@RequestBody UpdateUserPasswordDto updateUserPasswordDto){
        return userService.updatePassword(updateUserPasswordDto);
    }


    @PostMapping("delete")
    public ResponseDto deleteUser(@RequestBody UserDeleteDto userDeleteDto){
        return userService.deleteUser(userDeleteDto.getUsername());
    }
}
