package com.userService.demo.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPasswordDto {
    private String username;
    private String password;
}
