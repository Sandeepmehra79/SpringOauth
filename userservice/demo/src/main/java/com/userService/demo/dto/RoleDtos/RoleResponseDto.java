package com.userService.demo.dto.RoleDtos;

import com.userService.demo.dto.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleResponseDto extends ResponseDto {
    private Long id;
    private String name;
}
