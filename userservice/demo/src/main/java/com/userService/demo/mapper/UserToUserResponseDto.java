package com.userService.demo.mapper;

import com.userService.demo.dto.UserDto.UserResponseDto;
import com.userService.demo.model.Role;
import com.userService.demo.model.User;

public class UserToUserResponseDto {
    public static UserResponseDto convert(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setRoles(user.getRoles().stream()
                        .map(Role::getName).toList());
        userResponseDto.setMessage("The user with username "+user.getUsername()+" has been successfully created.");
        return userResponseDto;
    }
}
