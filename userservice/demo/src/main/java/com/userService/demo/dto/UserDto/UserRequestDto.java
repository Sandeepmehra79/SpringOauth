package com.userService.demo.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String username;
    private String password;
    private List<String> roles;
}
