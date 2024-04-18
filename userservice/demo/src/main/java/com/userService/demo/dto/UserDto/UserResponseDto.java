package com.userService.demo.dto.UserDto;

import com.userService.demo.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class UserResponseDto extends ResponseDto {
    private Long id;
    private String email;
    private String username;
    private List<String> roles;
}
