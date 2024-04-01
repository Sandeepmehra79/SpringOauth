package com.userService.demo.controller;

import com.userService.demo.dto.UserDto.UpdateUserPasswordDto;
import com.userService.demo.dto.UserDto.UserAuthDto;
import com.userService.demo.dto.UserDto.UserRequestDto;
import com.userService.demo.dto.UserDto.UserResponseDto;
import com.userService.demo.exception.RoleNotFoundException;
import com.userService.demo.exception.UserNotFoundException;
import com.userService.demo.service.JwtService;
import com.userService.demo.service.UserInfoService;
import com.userService.demo.service.UserService;
//import io.jsonwebtoken.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("{id}")
    public UserResponseDto getUserById(@PathVariable UUID id) throws UserNotFoundException {
        return userService.getUserById(id);
    }
    @PostMapping("create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) throws RoleNotFoundException {
        return userService.createUser(userRequestDto);
    }
    @GetMapping("")
    public List<UserResponseDto> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("home")
    public String homePage(){
        System.out.println("this is home function of user home");
        return "Hello users home page";
    }


    @PostMapping("updatePassword")
    public UserResponseDto updatePassword(@RequestBody UpdateUserPasswordDto updateUserPasswordDto){
        return userService.updatePassword(updateUserPasswordDto);
    }

    @PostMapping("generateToken")
    public String authenticateAndGetToken(@RequestBody UserAuthDto userAuthDto) throws UserNotFoundException {
        // confirming if the username and password is valid or not
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthDto.getUsername(), userAuthDto.getPassword()));
        // checking if the authentication was provided on the above authentication object.
        if(authentication.isAuthenticated()){
            // if the authentication is true then only we will assign the token
            return jwtService.createToken(userAuthDto.getUsername());
        } else {
            throw new UserNotFoundException("Invalid user request");
        }
    }

}
